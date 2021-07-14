package guru.springframework.services;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

public class RecipeServiceImplTest {

  RecipeServiceImpl recipeService;

  @Mock RecipeRepository recipeRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    recipeService = new RecipeServiceImpl(recipeRepository);
  }

  @Test
  public void testGetRecipes() {

    Recipe recipe = new Recipe();
    Set<Recipe> recipeData = new HashSet<>();
    recipeData.add(recipe);
    Mockito.when(recipeRepository.findAll()).thenReturn(recipeData);

    Set<Recipe> recipes = recipeService.getRecipes();

    assertEquals(1, recipes.size());
    Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
  }
}
