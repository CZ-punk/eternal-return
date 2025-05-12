package demo.eternalreturn.presentation.dto.response.eternalreturn.user;

import com.querydsl.core.annotations.QueryProjection;
import demo.eternalreturn.domain.model.eternal_return.user.CharacterStats;
import demo.eternalreturn.domain.model.eternal_return.user.TopRank;
import demo.eternalreturn.domain.model.eternal_return.user.UserStats;
import demo.eternalreturn.presentation.dto.response.PageResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ResTopRankDto {

    private Integer seasonId;
    private PageResponseDto<UserRankDto> userRankDtoPage;

    @Data
    public static class UserRankDto {
        private Integer userNum;
        private Integer rank;
        private String nickname;
        private Integer mmr;

        private Double averageRank;
        private Double averageWin;
        private Double top3;
        private Double averageKill;
        private Integer escapeCount;
        private Integer totalGameCount;

        private MainExperimentDto mainExperiment;

        @Data
        public static class MainExperimentDto {

            private Integer experimentCode1;
            private Double usage1;
            private Integer experimentCode2;
            private Double usage2;
            private Integer experimentCode3;
            private Double usage3;

            public MainExperimentDto(List<CharacterStats> list) {
                Integer[] codes = list.stream()
                        .map(CharacterStats::getCharacterCode)
                        .toArray(Integer[]::new);

                this.experimentCode1 = codes.length > 0 ? codes[0] : null;
                this.experimentCode2 = codes.length > 1 ? codes[1] : null;
                this.experimentCode3 = codes.length > 2 ? codes[2] : null;

                double totalUsage = list.stream()
                        .mapToDouble(CharacterStats::getUsages)
                        .sum();

                this.usage1 = codes.length > 0 ? list.get(0).getUsages() : null;
                this.usage2 = codes.length > 1 ? totalUsage / codes[1] : null;
                this.usage3 = codes.length > 2 ? totalUsage / codes[2] : null;
            }
        }

        public UserRankDto(TopRank topRank, UserStats userStats) {
            this.userNum = topRank.getUserNum();
            this.rank = topRank.getRank();
            this.nickname = topRank.getNickname();
            this.mmr = topRank.getMmr();

            this.averageRank = userStats.getAverageRank();
            this.averageKill = userStats.getAverageKills();
            this.averageWin = userStats.getTop1();
            this.top3 = userStats.getTop3();
            this.totalGameCount = userStats.getTotalGames();
            this.escapeCount = userStats.getEscapeCount();
        }

        public void connectMainExperiment(MainExperimentDto mainExperimentDto) {
            this.mainExperiment = mainExperimentDto;
        }
    }
}