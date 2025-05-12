package demo.eternalreturn.domain.model.eternal_return.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "user_stats")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserStats {

    @Id @Column(unique = true, nullable = false, name = "user_num")
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

    @LastModifiedDate
    @Builder.Default
    private LocalDateTime updateAt = LocalDateTime.now();

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

        updateAt = LocalDateTime.now();
        return this;
    }

}
