package demo.eternalreturn.presentation.dto.response.eternalreturn.user;

import demo.eternalreturn.domain.model.eternal_return.user.UserStats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResUserStatsDto {

    private Integer userNum;        // user pk
    private String nickname;        // nickname
    private Integer mmr;            // mmr

    private Integer totalGames;     // 총 게임 수
    private Double averageKills;    // 게임 당 평균 킬 수
    private Double averageDeaths;   // 평균 사망 수 == 총 사망 수 / 총 게임 수
    private Double averageTeamKills;// 평균 TK 수 == 총 TK 수 / 총 게임 수
    private Double averageRank;     // 평균 등수 (ex. 1.00 ~ 8.00)

    private Integer rank;           // 순위 (ex. 21000등)
    private Double rankPercent;     // 상위 % (ex. 21000등의 상위 %)
    private Integer escapeCount;    // 탈출 횟수 
    private Integer seasonId;       // 시즌 ID

    private Double top1;            // 1등 이내 %
    private Double top2;            // 2등 이내 %
    private Double top3;            // 3등 이내 %
    
    private Integer totalWins;      // 1등 횟수

    public ResUserStatsDto(UserStats userStats) {
        userNum = userStats.getUserNum();
        nickname = userStats.getNickname();
        mmr = userStats.getMmr();
        totalGames = userStats.getTotalGames();
        averageKills = userStats.getAverageKills();
        averageDeaths = (double) userStats.getTotalDeaths() /  userStats.getTotalGames();
        averageTeamKills = (double) userStats.getTotalTeamKills() /   userStats.getTotalGames();
        averageRank = userStats.getAverageRank();
        rank = userStats.getRank();
        rankPercent = userStats.getRankPercent();
        escapeCount = userStats.getEscapeCount();
        seasonId = userStats.getSeasonId();
        top1 = userStats.getTop1();
        top2 = userStats.getTop2();
        top3 = userStats.getTop3();
        totalWins = userStats.getTotalWins();
    }
}
