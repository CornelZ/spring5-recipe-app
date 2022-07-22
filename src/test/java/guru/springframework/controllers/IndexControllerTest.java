package guru.springframework.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

public class IndexControllerTest {

  private IndexController indexController;
  @Mock private RecipeService recipeService;
  @Mock private Model model;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    indexController = new IndexController(recipeService);
  }

  @Test
  public void testMovMVC() throws Exception {
    MockMvc mocMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    mocMvc
        .perform(MockMvcRequestBuilders.get("/")) //
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("index"));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testGetIndexPage() {

    // given
    Set<Recipe> recipes = new HashSet<>();
    recipes.add(new Recipe());

    Recipe recipe = new Recipe();
    recipe.setId(1L);

    recipes.add(recipe);

    when(recipeService.getRecipes()).thenReturn(recipes);

    ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

    // when
    String index = indexController.getIndexPage(model);

    // then
    assertEquals("index", index);

    verify(recipeService, times(1)).getRecipes();
    verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

    Set<Recipe> setInController = argumentCaptor.getValue();
    assertEquals(2, setInController.size());
  }
}
