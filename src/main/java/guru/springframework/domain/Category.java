package guru.springframework.domain;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Set;

/** Created by jt on 6/13/17. */
@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @ManyToMany(mappedBy = "categories")
  private Set<Recipe> recipes;
}
