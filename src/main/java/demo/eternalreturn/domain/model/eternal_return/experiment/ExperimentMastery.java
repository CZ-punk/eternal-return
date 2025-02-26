package demo.eternalreturn.domain.model.eternal_return.experiment;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "experiment_mastery")
public class ExperimentMastery {

    @Id
    private Integer code;

    private String weapon1;
    private String weapon2;
    private String weapon3;
    private String weapon4;

    private String combat1;
    private String combat2;

    private String survival1;
    private String survival2;
    private String survival3;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ExperimentMastery that = (ExperimentMastery) obj;

        return Objects.equals(code, that.code) &&
                Objects.equals(weapon1, that.weapon1) &&
                Objects.equals(weapon2, that.weapon2) &&
                Objects.equals(weapon3, that.weapon3) &&
                Objects.equals(weapon4, that.weapon4) &&
                Objects.equals(combat1, that.combat1) &&
                Objects.equals(combat2, that.combat2) &&
                Objects.equals(survival1, that.survival1) &&
                Objects.equals(survival2, that.survival2) &&
                Objects.equals(survival3, that.survival3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                code, weapon1, weapon2, weapon3, weapon4, combat1, combat2, survival1, survival2, survival3
        );
    }

    public void update(ExperimentMastery getExperimentMastery) {
        this.weapon1 = getExperimentMastery.getWeapon1();
        this.weapon2 = getExperimentMastery.getWeapon2();
        this.weapon3 = getExperimentMastery.getWeapon3();
        this.weapon4 = getExperimentMastery.getWeapon4();
        this.combat1 = getExperimentMastery.getCombat1();
        this.combat2 = getExperimentMastery.getCombat2();
        this.survival1 = getExperimentMastery.getSurvival1();
        this.survival2 = getExperimentMastery.getSurvival2();
        this.survival3 = getExperimentMastery.getSurvival3();
    }
}
