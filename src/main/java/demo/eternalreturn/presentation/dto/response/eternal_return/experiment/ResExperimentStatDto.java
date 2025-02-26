package demo.eternalreturn.presentation.dto.response.eternal_return.experiment;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResExperimentStatDto {

    private Integer code;
    private String name;

    private Integer maxHp;
    private Integer maxSp;

    private Integer attackPower;
    private Integer defense;

    private Double hpRegen;
    private Double spRegen;

    private Double attackSpeed;
    private Double moveSpeed;
    private Double sightRange;

    private LevelUpStat levelUpStat;

    @Data
    @NoArgsConstructor
    protected static class LevelUpStat {

        private Integer maxHp;
        private Integer maxSp;

        private Double attackPower;
        private Double defense;

        private Double hpRegen;
        private Double spRegen;

        @QueryProjection
        public LevelUpStat(Integer maxHp, Integer maxSp, Double attackPower, Double defense, Double hpRegen, Double spRegen) {
            this.maxHp = maxHp;
            this.maxSp = maxSp;
            this.attackPower = attackPower;
            this.defense = defense;
            this.hpRegen = hpRegen;
            this.spRegen = spRegen;
        }
    }

    @QueryProjection
    public ResExperimentStatDto(Integer code, String name, Integer maxHp, Integer maxSp, Integer attackPower, Integer defense, Double hpRegen, Double spRegen, Double attackSpeed, Double moveSpeed, Double sightRange, LevelUpStat levelUpStat) {
        this.code = code;
        this.name = name;
        this.maxHp = maxHp;
        this.maxSp = maxSp;
        this.attackPower = attackPower;
        this.defense = defense;
        this.hpRegen = hpRegen;
        this.spRegen = spRegen;
        this.attackSpeed = attackSpeed;
        this.moveSpeed = moveSpeed;
        this.sightRange = sightRange;
        this.levelUpStat = levelUpStat;
    }
}
