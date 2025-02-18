package guru.springframework.repositories;

import static org.junit.Assert.assertEquals;

import guru.springframework.domain.UnitOfMeasure;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

  @Autowired private UnitOfMeasureRepository unitOfMeasureRepository;

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testFindByDescriptionTeaspoon() {
    final Optional<UnitOfMeasure> uomOptional =
        unitOfMeasureRepository.findByDescription("Teaspoon");
    assertEquals("Teaspoon", uomOptional.get().getDescription());
  }

  @Test
  public void testFindByDescriptionTablespoon() {
    final Optional<UnitOfMeasure> uomOptional =
        unitOfMeasureRepository.findByDescription("Tablespoon");
    assertEquals("Tablespoon", uomOptional.get().getDescription());
  }
}
