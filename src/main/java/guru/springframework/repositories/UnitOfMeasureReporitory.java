package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import guru.springframework.domain.UnitOfMeasure;

public interface UnitOfMeasureReporitory extends CrudRepository<UnitOfMeasure, Long> {}
