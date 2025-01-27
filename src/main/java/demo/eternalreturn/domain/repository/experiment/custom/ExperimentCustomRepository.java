package demo.eternalreturn.domain.repository.experiment.custom;

import demo.eternalreturn.presentation.controller.dto.response.experiment.ResExperimentStatDto;

public interface ExperimentCustomRepository {

    ResExperimentStatDto searchExperimentStatByExperimentCode(Integer experimentCode);

}
