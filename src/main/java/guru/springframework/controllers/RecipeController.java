package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.RecipeService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class RecipeController {

  private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
  private final RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping("/recipe/{id}/show")
  public String showById(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
    return "recipe/show";
  }

  @GetMapping("/recipe/new")
  public String newRecipt(Model model) {
    model.addAttribute("recipe", new RecipeCommand());
    return RECIPE_RECIPEFORM_URL;
  }

  @GetMapping("/recipe/{id}/update")
  public String updateRecipt(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
    return RECIPE_RECIPEFORM_URL;
  }

  @PostMapping("recipe")
  public String saveOrUpdate(
      @Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      bindingResult
          .getAllErrors()
          .forEach(objectError -> log.error("objectError: {}", objectError));
      return RECIPE_RECIPEFORM_URL;
    }
    RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
    return "redirect:/recipe/" + savedCommand.getId() + "/show";
  }

  @GetMapping("recipe/{id}/delete")
  public String deleteById(@PathVariable String id) {
    log.debug("Deleting id: " + id);
    recipeService.deleteById(Long.valueOf(id));
    return "redirect:/";
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public ModelAndView handleNotFound(Exception ex) {
    log.error("Handling not found exception: {}", ex.getMessage());
    ModelAndView modelAndView = new ModelAndView("404error");
    modelAndView.addObject("ex", ex);

    return modelAndView;
  }
}
