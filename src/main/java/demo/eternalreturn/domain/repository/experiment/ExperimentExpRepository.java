package demo.eternalreturn.domain.repository.experiment;

import demo.eternalreturn.domain.model.experiment.ExperimentExp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperimentExpRepository extends JpaRepository<ExperimentExp, Integer> {
}
