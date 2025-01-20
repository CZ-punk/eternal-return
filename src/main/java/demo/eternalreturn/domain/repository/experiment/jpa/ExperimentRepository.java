package demo.eternalreturn.domain.repository.experiment.jpa;

import demo.eternalreturn.domain.model.experiment.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperimentRepository extends JpaRepository<Experiment, Integer> {

}
