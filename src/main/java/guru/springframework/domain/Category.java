package guru.springframework.domain;

import javax.persistence.*;
import lombok.Data;
import java.util.Set;

/** Created by jt on 6/13/17. */
@Entity
@Data
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
  private Set<Recipe> recipes;
}
