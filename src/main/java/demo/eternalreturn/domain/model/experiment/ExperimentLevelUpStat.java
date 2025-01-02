package demo.eternalreturn.domain.model.experiment;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ExperimentLevelUpStat that = (ExperimentLevelUpStat) obj;

        return Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(maxHp, that.maxHp) &&
                Objects.equals(maxSp, that.maxSp) &&
                Objects.equals(attackPower, that.attackPower) &&
                Objects.equals(defense, that.defense) &&
                Objects.equals(criticalChance, that.criticalChance) &&
                Objects.equals(hpRegen, that.hpRegen) &&
                Objects.equals(spRegen, that.spRegen) &&
                Objects.equals(attackSpeed, that.attackSpeed) &&
                Objects.equals(moveSpeed, that.moveSpeed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                code, name, maxHp, maxSp, attackPower, defense, criticalChance, hpRegen, spRegen, attackSpeed, moveSpeed
        );
    }

    public void update(ExperimentLevelUpStat getExperimentLevelUpStat) {
        this.name = getExperimentLevelUpStat.getName();
        this.maxHp = getExperimentLevelUpStat.getMaxHp();
        this.maxSp = getExperimentLevelUpStat.getMaxSp();
        this.attackPower = getExperimentLevelUpStat.getAttackPower();
        this.defense = getExperimentLevelUpStat.getDefense();
        this.criticalChance = getExperimentLevelUpStat.getCriticalChance();
        this.hpRegen = getExperimentLevelUpStat.getHpRegen();
        this.spRegen = getExperimentLevelUpStat.getSpRegen();
        this.attackSpeed = getExperimentLevelUpStat.getAttackSpeed();
        this.moveSpeed = getExperimentLevelUpStat.getMoveSpeed();
    }
}