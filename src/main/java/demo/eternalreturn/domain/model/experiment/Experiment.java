package demo.eternalreturn.domain.model.experiment;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "experiment")
public class Experiment {

    @Id
    private Integer code;

    private String name;
    private Integer maxHp;
    private Integer maxSp;

    private String strLearnStartSkill;
    private String strUsePointLearnStartSkill;

    private Integer initExtraPoint;
    private Integer maxExtraPoint;

    private Integer attackPower;
    private Integer defense;

    private Double hpRegen;
    private Double spRegen;

    private Double attackSpeed;
    private Double attackSpeedLimit;
    private Double attackSpeedMin;

    private Double moveSpeed;
    private Double sightRange;

    @Builder.Default
    @OneToMany(mappedBy = "experiment", fetch = FetchType.LAZY)
    private List<ExperimentAttribute> experimentAttributeList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private ExperimentLevelUpStat experimentLevelUpStat;

    @OneToOne(fetch = FetchType.LAZY)
    private ExperimentMastery experimentMastery;

    @Builder.Default
    @OneToMany(mappedBy = "experiment", fetch = FetchType.LAZY)
    private List<ExperimentSkin> experimentSkinList = new ArrayList<>();

    public void connectExperimentAttribute(ExperimentAttribute experimentAttribute) {
        experimentAttributeList.add(experimentAttribute);
        experimentAttribute.setExperiment(this);
    }
    public void connectExperimentLevelUpStat(ExperimentLevelUpStat experimentLevelUpStat) {
        this.experimentLevelUpStat = experimentLevelUpStat;

    }

    public void connectExperimentMastery(ExperimentMastery experimentMastery) {
        this.experimentMastery = experimentMastery;
    }

    public void connectExperimentSkin(ExperimentSkin experimentSkin) {
        experimentSkinList.add(experimentSkin);
        experimentSkin.setExperiment(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Experiment that = (Experiment) obj;

        return Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(maxHp, that.maxHp) &&
                Objects.equals(maxSp, that.maxSp) &&
                Objects.equals(strLearnStartSkill, that.strLearnStartSkill) &&
                Objects.equals(strUsePointLearnStartSkill, that.strUsePointLearnStartSkill) &&
                Objects.equals(initExtraPoint, that.initExtraPoint) &&
                Objects.equals(maxExtraPoint, that.maxExtraPoint) &&
                Objects.equals(attackPower, that.attackPower) &&
                Objects.equals(defense, that.defense) &&
                Objects.equals(hpRegen, that.hpRegen) &&
                Objects.equals(spRegen, that.spRegen) &&
                Objects.equals(attackSpeed, that.attackSpeed) &&
                Objects.equals(attackSpeedLimit, that.attackSpeedLimit) &&
                Objects.equals(attackSpeedMin, that.attackSpeedMin) &&
                Objects.equals(moveSpeed, that.moveSpeed) &&
                Objects.equals(sightRange, that.sightRange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                code, name, maxHp, maxSp, strLearnStartSkill, strUsePointLearnStartSkill,
                initExtraPoint, maxExtraPoint, attackPower, defense,
                hpRegen, spRegen, attackSpeed, attackSpeedLimit, attackSpeedMin, moveSpeed, sightRange
        );
    }
}