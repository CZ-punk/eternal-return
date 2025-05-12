package demo.eternalreturn.domain.repository.eternalreturn.experiment.jpa;

import demo.eternalreturn.domain.model.eternal_return.experiment.ExperimentExp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperimentExpRepository extends JpaRepository<ExperimentExp, Integer> {
}
