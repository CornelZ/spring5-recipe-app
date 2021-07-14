package guru.springframework.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

public class IndexControllerTest {

  IndexController indexController;
  @Mock RecipeService recipeService;
  @Mock Model model;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    indexController = new IndexController(recipeService);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testGetIndexPage() {

    // given
    Set<Recipe> recipes = new HashSet<>();
    recipes.add(new Recipe());

    Recipe recipe = new Recipe();
    recipe.setDescription("description");
    recipes.add(recipe);

    when(recipeService.getRecipes()).thenReturn(recipes);

    ArgumentCaptor<Set<Recipe>> argCaptor = ArgumentCaptor.forClass(Set.class);

    // when
    String viewName = indexController.getIndexPage(model);

    // then
    assertEquals("index", viewName);
    verify(recipeService, times(1)).getRecipes();
    verify(model, times(1)).addAttribute(eq("recipes"), argCaptor.capture());
    Set<Recipe> setInController = argCaptor.getValue();
    assertEquals(2, setInController.size());
  }
}
