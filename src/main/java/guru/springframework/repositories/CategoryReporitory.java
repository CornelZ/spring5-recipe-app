package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import guru.springframework.domain.Category;

public interface CategoryReporitory extends CrudRepository<Category, Long> {}
