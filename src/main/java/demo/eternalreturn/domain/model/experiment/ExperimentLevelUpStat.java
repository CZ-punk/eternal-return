package demo.eternalreturn.domain.model.experiment;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "experiment_level_up_stat")
public class ExperimentLevelUpStat {

    @Id
    private Integer code;

    private String name;
    private Integer maxHp;
    private Integer maxSp;

    private Double attackPower;
    private Double defense;

    private Double criticalChance;
    private Double hpRegen;
    private Double spRegen;
    private Double attackSpeed;
    private Double moveSpeed;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experiment_code")
    private Experiment experiment;
}
