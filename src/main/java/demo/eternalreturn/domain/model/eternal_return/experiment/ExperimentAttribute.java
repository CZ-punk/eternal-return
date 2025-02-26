package demo.eternalreturn.domain.model.eternal_return.experiment;

import demo.eternalreturn.domain.constant.WeaponType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "experiment_attribute")
public class ExperimentAttribute {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "character_code")
    private Integer characterCode;
    @Column(name = "`character`")
    private String character;
    @Enumerated(EnumType.STRING)
    private WeaponType mastery;

    private Integer controlDifficulty;
    private Integer attack;
    private Integer defense;
    private Integer disruptor;
    private Integer move;
    private Integer assistance;


    @Override
    public int hashCode() {
        return Objects.hash(characterCode, character, mastery);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ExperimentAttribute that = (ExperimentAttribute) obj;

        return Objects.equals(characterCode, that.characterCode) &&
                Objects.equals(character, that.character) &&
                Objects.equals(mastery, that.mastery);
    }
}
