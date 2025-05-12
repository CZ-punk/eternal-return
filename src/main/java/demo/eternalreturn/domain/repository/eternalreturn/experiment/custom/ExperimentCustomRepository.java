package demo.eternalreturn.domain.repository.eternalreturn.experiment.custom;

import demo.eternalreturn.presentation.dto.response.eternalreturn.experiment.ResExperimentStatDto;

public interface ExperimentCustomRepository {

    ResExperimentStatDto searchExperimentStatByExperimentCode(Integer experimentCode);

}
