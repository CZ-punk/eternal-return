package demo.eternalreturn.domain.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.eternalreturn.domain.model.CharacterStats;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "user_stats")
public class UserStats {

    @Id
    @Column(unique = true, nullable = false)
    private Integer userNum;
    private Integer seasonId;

    private Integer matchingMode;
    private Integer matchingTeamMode;
    private Integer mmr;
    private String nickname;

    @Column(name = "`rank`")
    private Integer rank;
    private Integer rankSize;
    private Integer totalGames;
    private Integer totalWins;
    private Integer totalTeamKills;
    private Integer totalDeaths;
    private Integer escapeCount;
    private Double rankPercent;
    private Double averageRank;
    private Double averageKills;
    private Double averageAssistants;
    private Double averageHunts;
    private Double top1;
    private Double top2;
    private Double top3;
    private Double top5;
    private Double top7;

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "userStats", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CharacterStats> characterStats = new ArrayList<>();

    public CharacterStats addCharacterStats(CharacterStats stats) {
        characterStats.add(stats);
        stats.setUserStats(this);
        return stats;
    }

    public UserStats update(UserStats result) {
        seasonId = result.getSeasonId();
        matchingMode = result.getMatchingMode();
        matchingTeamMode = result.getMatchingTeamMode();
        mmr = result.getMmr();
        nickname = result.getNickname();
        rank = result.getRank();
        rankSize = result.getRankSize();
        totalGames = result.getTotalGames();
        totalWins = result.getTotalWins();
        totalTeamKills = result.getTotalTeamKills();
        totalDeaths = result.getTotalDeaths();
        escapeCount = result.getEscapeCount();
        rankPercent = result.getRankPercent();
        averageRank = result.getAverageRank();
        averageKills = result.getAverageKills();
        averageAssistants = result.getAverageAssistants();
        averageHunts = result.getAverageHunts();
        top1 = result.getTop1();
        top2 = result.getTop2();
        top3 = result.getTop3();
        top5 = result.getTop5();
        top7 = result.getTop7();

        characterStats.clear();
        characterStats = result.getCharacterStats();

        return this;
    }
}
