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

    private Integer code;

    @Enumerated(EnumType.STRING)
    private WeaponType mastery;

    private Integer controlDifficulty;
    private Integer attack;
    private Integer defense;
    private Integer disruptor;
    private Integer move;
    private Integer assistance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experiment_code")
    private Experiment experiment;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ExperimentAttribute that = (ExperimentAttribute) obj;

        return Objects.equals(code, that.code) &&
                Objects.equals(mastery, that.mastery) &&
                Objects.equals(controlDifficulty, that.controlDifficulty) &&
                Objects.equals(attack, that.attack) &&
                Objects.equals(defense, that.defense) &&
                Objects.equals(disruptor, that.disruptor) &&
                Objects.equals(move, that.move) &&
                Objects.equals(assistance, that.assistance) &&
                Objects.equals(experiment, that.experiment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                code, mastery, controlDifficulty, attack, defense, disruptor, move, assistance, experiment
        );
    }

}
