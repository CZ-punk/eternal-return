package demo.eternalreturn.domain.repository.experiment.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.eternalreturn.presentation.controller.dto.response.experiment.QResExperimentStatDto;
import demo.eternalreturn.presentation.controller.dto.response.experiment.QResExperimentStatDto_LevelUpStat;
import demo.eternalreturn.presentation.controller.dto.response.experiment.ResExperimentStatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static demo.eternalreturn.domain.model.experiment.QExperiment.experiment;
import static demo.eternalreturn.domain.model.experiment.QExperimentLevelUpStat.experimentLevelUpStat;

@Repository
@RequiredArgsConstructor
public class ExperimentCustomRepositoryImpl implements ExperimentCustomRepository {

    @Autowired
    private final JPAQueryFactory queryFactory;

    /**
     * Experiment 기본 Stat && Level Up Stat Dto 반환 ( @QueryProjection 사용 )
     */
    @Override
    public ResExperimentStatDto searchExperimentStatByExperimentCode(Integer experimentCode) {
        return queryFactory.select(new QResExperimentStatDto(
                        experiment.code,
                        experiment.name,
                        experiment.maxHp,
                        experiment.maxSp,
                        experiment.attackPower,
                        experiment.defense,
                        experiment.hpRegen,
                        experiment.spRegen,
                        experiment.attackSpeed,
                        experiment.moveSpeed,
                        experiment.sightRange,
                        new QResExperimentStatDto_LevelUpStat(
                                experimentLevelUpStat.maxHp,
                                experimentLevelUpStat.maxSp,
                                experimentLevelUpStat.attackPower,
                                experimentLevelUpStat.defense,
                                experimentLevelUpStat.hpRegen,
                                experimentLevelUpStat.spRegen
                        )
                ))
                .from(experiment)
                .join(experimentLevelUpStat)
                .on(experiment.code.eq(experimentLevelUpStat.code))
                .where(experiment.code.eq(experimentCode))
                .fetchOne();
    }
}
