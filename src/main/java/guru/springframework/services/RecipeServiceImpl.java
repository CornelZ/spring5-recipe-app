package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.RecipeRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Created by jt on 6/13/17. */
@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;
  private final RecipeCommandToRecipe recipeCommandToRecipe;
  private final RecipeToRecipeCommand recipeToRecipeCommand;

  public RecipeServiceImpl(
      RecipeRepository recipeRepository,
      RecipeCommandToRecipe recipeCommandToRecipe,
      RecipeToRecipeCommand recipeToRecipeCommand) {
    this.recipeRepository = recipeRepository;
    this.recipeCommandToRecipe = recipeCommandToRecipe;
    this.recipeToRecipeCommand = recipeToRecipeCommand;
  }

  @Override
  public Set<Recipe> getRecipes() {
    Set<Recipe> recipeSet = new HashSet<>();
    recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
    return recipeSet;
  }

  @Override
  public Recipe findById(Long id) {

    Optional<Recipe> recOptional = recipeRepository.findById(id);
    if (!recOptional.isPresent()) {
      throw new NotFoundException("Recipe not found, for ID value: " + id);
    }
    return recOptional.get();
  }

  @Override
  @Transactional
  public RecipeCommand findCommandById(Long l) {
    return recipeToRecipeCommand.convert(findById(l));
  }

  @Override
  @Transactional
  public RecipeCommand saveRecipeCommand(RecipeCommand command) {
    Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
    Recipe savedRecipe = recipeRepository.save(detachedRecipe);
    log.debug("Saved RecipeId:" + savedRecipe.getId());
    return recipeToRecipeCommand.convert(savedRecipe);
  }

  @Override
  public void deleteById(Long idToDelete) {
    recipeRepository.deleteById(idToDelete);
  }
}
