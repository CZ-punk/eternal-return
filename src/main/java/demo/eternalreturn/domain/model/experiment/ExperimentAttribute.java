package demo.eternalreturn.domain.model.experiment;

import demo.eternalreturn.domain.model.constant.WeaponType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "experiment_attribute")
public class ExperimentAttribute {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer code;
    @Enumerated(EnumType.STRING)
    private WeaponType mastery;

    private Integer controlDifficulty;
    private Integer attack;
    private Integer defense;
    private Integer disruptor;
    private Integer move;
    private Integer assistance;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ExperimentAttribute that = (ExperimentAttribute) obj;

        return Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(mastery, that.mastery) &&
                Objects.equals(controlDifficulty, that.controlDifficulty) &&
                Objects.equals(attack, that.attack) &&
                Objects.equals(defense, that.defense) &&
                Objects.equals(disruptor, that.disruptor) &&
                Objects.equals(move, that.move) &&
                Objects.equals(assistance, that.assistance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                code, name, mastery, controlDifficulty, attack, defense, disruptor, move, assistance
        );
    }

    public void update(ExperimentAttribute getExperimentAttribute) {
        this.controlDifficulty = getExperimentAttribute.getControlDifficulty();
        this.attack = getExperimentAttribute.getAttack();
        this.defense = getExperimentAttribute.getDefense();
        this.disruptor = getExperimentAttribute.getDisruptor();
        this.move = getExperimentAttribute.getMove();
        this.assistance = getExperimentAttribute.getAssistance();
    }
}
