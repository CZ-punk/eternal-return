package demo.eternalreturn.presentation.dto.response.eternalreturn.experiment;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * demo.eternalreturn.presentation.dto.response.eternalreturn.experiment.QResExperimentStatDto is a Querydsl Projection type for ResExperimentStatDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResExperimentStatDto extends ConstructorExpression<ResExperimentStatDto> {

    private static final long serialVersionUID = 1858340968L;

    public QResExperimentStatDto(com.querydsl.core.types.Expression<Integer> code, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> maxHp, com.querydsl.core.types.Expression<Integer> maxSp, com.querydsl.core.types.Expression<Integer> attackPower, com.querydsl.core.types.Expression<Integer> defense, com.querydsl.core.types.Expression<Double> hpRegen, com.querydsl.core.types.Expression<Double> spRegen, com.querydsl.core.types.Expression<Double> attackSpeed, com.querydsl.core.types.Expression<Double> moveSpeed, com.querydsl.core.types.Expression<Double> sightRange, com.querydsl.core.types.Expression<? extends ResExperimentStatDto.LevelUpStat> levelUpStat) {
        super(ResExperimentStatDto.class, new Class<?>[]{int.class, String.class, int.class, int.class, int.class, int.class, double.class, double.class, double.class, double.class, double.class, ResExperimentStatDto.LevelUpStat.class}, code, name, maxHp, maxSp, attackPower, defense, hpRegen, spRegen, attackSpeed, moveSpeed, sightRange, levelUpStat);
    }

}

