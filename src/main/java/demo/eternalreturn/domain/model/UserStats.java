package demo.eternalreturn.domain.model;

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
    @OneToMany(mappedBy = "userStats", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CharacterStats> characterStatsList = new ArrayList<>();

    public void connectCharacterStats(CharacterStats characterStats) {
        characterStatsList.add(characterStats);
        characterStats.setUserStats(this);
    }
}
