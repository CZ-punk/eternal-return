package demo.eternalreturn.presentation.dto.response.eternal_return.experiment;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * demo.eternalreturn.presentation.dto.response.eternal_return.experiment.QResExperimentStatDto_LevelUpStat is a Querydsl Projection type for LevelUpStat
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResExperimentStatDto_LevelUpStat extends ConstructorExpression<ResExperimentStatDto.LevelUpStat> {

    private static final long serialVersionUID = -153894018L;

    public QResExperimentStatDto_LevelUpStat(com.querydsl.core.types.Expression<Integer> maxHp, com.querydsl.core.types.Expression<Integer> maxSp, com.querydsl.core.types.Expression<Double> attackPower, com.querydsl.core.types.Expression<Double> defense, com.querydsl.core.types.Expression<Double> hpRegen, com.querydsl.core.types.Expression<Double> spRegen) {
        super(ResExperimentStatDto.LevelUpStat.class, new Class<?>[]{int.class, int.class, double.class, double.class, double.class, double.class}, maxHp, maxSp, attackPower, defense, hpRegen, spRegen);
    }

}

