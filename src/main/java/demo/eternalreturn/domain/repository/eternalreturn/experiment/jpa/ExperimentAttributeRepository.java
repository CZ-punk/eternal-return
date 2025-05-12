package demo.eternalreturn.domain.repository.eternalreturn.experiment.jpa;

import demo.eternalreturn.domain.model.eternal_return.experiment.ExperimentAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperimentAttributeRepository extends JpaRepository<ExperimentAttribute, Integer> {
}
