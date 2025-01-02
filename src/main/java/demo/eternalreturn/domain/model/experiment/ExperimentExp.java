package demo.eternalreturn.domain.model.experiment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "experiment_exp")
public class ExperimentExp {

    @Id
    private Integer level;
    private Integer levelUpExp;

    public void update(ExperimentExp getExperimentExp) {
        this.levelUpExp = getExperimentExp.getLevelUpExp();
    }
}
