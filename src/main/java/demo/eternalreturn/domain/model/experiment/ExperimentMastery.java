package demo.eternalreturn.domain.model.experiment;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experiment_code")
    private Experiment experiment;
}
