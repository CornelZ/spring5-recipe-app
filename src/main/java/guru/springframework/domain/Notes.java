package guru.springframework.domain;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ToString.Exclude @OneToOne private Recipe recipe;

  @Lob private String recipeNotes;
}
