package demo.eternalreturn.domain.repository.experiment.custom;

import demo.eternalreturn.presentation.dto.response.eternal_return.experiment.ResExperimentStatDto;

public interface ExperimentCustomRepository {

    ResExperimentStatDto searchExperimentStatByExperimentCode(Integer experimentCode);

}
