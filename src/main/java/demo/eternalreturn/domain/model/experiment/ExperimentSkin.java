package demo.eternalreturn.domain.model.experiment;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.profile.Fetch;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "experiment_skin")
public class ExperimentSkin {

    @Id
    private Integer code;   // 스킨고유ID = 1001000

    private String name;
    private Integer characterCode;  // 캐릭터고유ID = 1
    private Integer _index;
    private Integer grade;
    private Boolean eventFree;
    private String purchaseType;

    private String effectsPath;
    private String projectilesPath;
    private String objectPath;
    private String fxSoundPath;
    private String voicePath;
    private String weaponMountPath;
    private String weaponMountCommonPath;
    private String indicatorPath;


}