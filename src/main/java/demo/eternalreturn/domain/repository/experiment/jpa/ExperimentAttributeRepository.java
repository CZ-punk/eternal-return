package demo.eternalreturn.domain.repository.experiment.jpa;

import demo.eternalreturn.domain.model.experiment.ExperimentAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperimentAttributeRepository extends JpaRepository<ExperimentAttribute, Integer> {
}
