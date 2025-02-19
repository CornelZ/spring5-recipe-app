package guru.springframework.domain;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;

/** Created by jt on 6/13/17. */
@Data
@EqualsAndHashCode(exclude = {"categories", "ingredients"})
@Entity
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;
  private Integer prepTime;
  private Integer cookTime;
  private Integer servings;
  private String source;
  private String url;

  @Lob private String directions;

  @ToString.Exclude
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", fetch = FetchType.EAGER)
  private Set<Ingredient> ingredients = new HashSet<>();

  @Lob private Byte[] image;

  @Enumerated(value = EnumType.STRING)
  private Difficulty difficulty;

  @OneToOne(cascade = CascadeType.ALL)
  private Notes notes;

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "recipe_category",
      joinColumns = @JoinColumn(name = "recipe_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<Category> categories = new HashSet<>();

  public void setNotes(Notes notes) {
    if (notes != null) {
      this.notes = notes;
      notes.setRecipe(this);
    }
  }

  public Recipe addIngredient(Ingredient ingredient) {
    if (ingredient != null) {
      ingredient.setRecipe(this);
      this.ingredients.add(ingredient);
    }
    return this;
  }
}
