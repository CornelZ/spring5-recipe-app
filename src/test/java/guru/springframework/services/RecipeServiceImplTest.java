package guru.springframework.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.RecipeRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RecipeServiceImplTest {

  private RecipeService recipeService;

  @Mock private RecipeRepository recipeRepository;
  @Mock RecipeToRecipeCommand recipeToRecipeCommand;
  @Mock RecipeCommandToRecipe recipeCommandToRecipe;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    recipeService =
        new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void getRecipeByIdTest() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    Recipe recipeReturned = recipeService.findById(1L);

    assertNotNull("Null recipe returned", recipeReturned);
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, never()).findAll();
  }

  @Test
  public void testGetRecipes() {
    Recipe recipe = new Recipe();
    Set<Recipe> set = new HashSet<>();
    set.add(recipe);

    when(recipeRepository.findAll()).thenReturn(set);

    final Set<Recipe> recipes = recipeService.getRecipes();
    assertEquals(1, recipes.size());

    verify(recipeRepository, times(1)).findAll();
  }

  @Test
  public void testDeleteById() throws Exception {

    // given
    Long idToDelete = Long.valueOf(2L);

    // when
    recipeService.deleteById(idToDelete);

    // no 'when', since method has void return type

    // then
    verify(recipeRepository, times(1)).deleteById(anyLong());
  }

  @Test(expected = NotFoundException.class)
  public void getRecipeByIdTestNotFound() throws Exception {

    Optional<Recipe> recipeOptional = Optional.empty();

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    recipeService.findById(1L);
    // should go boom
  }
}
