package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

public interface IngredientService {
  IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

  IngredientCommand saveIngredientCommand(IngredientCommand command);

  public void deleteById(Long recipeId, Long idToDelete);
}
