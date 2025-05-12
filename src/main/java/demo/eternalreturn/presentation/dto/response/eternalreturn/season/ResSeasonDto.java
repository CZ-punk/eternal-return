package demo.eternalreturn.presentation.dto.response.eternalreturn.season;

import demo.eternalreturn.domain.model.eternal_return.season.Season;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResSeasonDto {

    private Integer seasonId;
    private String seasonName;
    private String seasonStart;
    private String seasonEnd;
    private Boolean isCurrent;

    public ResSeasonDto(Season season) {
        this.seasonId = season.getSeasonID();
        this.seasonName = season.getSeasonName();
        this.seasonStart = season.getSeasonStart();
        this.seasonEnd = season.getSeasonEnd();
        this.isCurrent = season.getIsCurrent() != null && season.getIsCurrent() == 1;
    }
}
