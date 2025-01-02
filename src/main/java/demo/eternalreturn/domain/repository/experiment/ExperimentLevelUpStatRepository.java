package demo.eternalreturn.domain.repository.experiment;

import demo.eternalreturn.domain.model.experiment.ExperimentLevelUpStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperimentLevelUpStatRepository extends JpaRepository<ExperimentLevelUpStat, Integer> {
}
