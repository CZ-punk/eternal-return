package demo.eternalreturn.domain.model.eternal_return.season;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "season")
public class Season {

    @Id
    @Column(name = "season_id")
    private Integer seasonID;
    private String seasonName;
    private String seasonStart;
    private String seasonEnd;
    private Integer isCurrent;

    public void update(Season season) {
        this.seasonName = season.getSeasonName();
        this.seasonStart = season.getSeasonStart();
        this.seasonEnd = season.getSeasonEnd();
        this.isCurrent = season.getIsCurrent();
    }
}
