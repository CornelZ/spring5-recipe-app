package guru.springframework.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import java.util.HashSet;
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

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    recipeService = new RecipeServiceImpl(recipeRepository);
  }

  @After
  public void tearDown() throws Exception {}

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
}
