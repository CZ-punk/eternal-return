package demo.eternalreturn.domain.repository.experiment;

import demo.eternalreturn.domain.model.experiment.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperimentRepository extends JpaRepository<Experiment, Integer> {

}
