package guru.springframework.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CategoryTest {

  private Category category;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  @Before
  public void setUp() throws Exception {
    category = new Category();
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testGetId() {
    final Long id = 4L;
    category.setId(id);
    assertEquals(id, category.getId());
  }

  @Test
  public void testGetDescription() {
    final String description = "description";
    category.setDescription(description);
    assertEquals(description, category.getDescription());
  }
}
