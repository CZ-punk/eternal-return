package demo.eternalreturn.domain.repository.eternalreturn.experiment.jpa;

import demo.eternalreturn.domain.model.eternal_return.experiment.ExperimentLevelUpStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperimentLevelUpStatRepository extends JpaRepository<ExperimentLevelUpStat, Integer> {
}
