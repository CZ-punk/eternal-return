package demo.eternalreturn.domain.repository.experiment.custom;

import demo.eternalreturn.application.dto.ResExperimentStatDto;

public interface ExperimentCustomRepository {

    ResExperimentStatDto searchExperimentStatByExperimentCode(Integer experimentCode);

}
