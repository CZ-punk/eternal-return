package demo.eternalreturn.application.service;

import demo.eternalreturn.presentation.controller.dto.response.ResponseDto;
import demo.eternalreturn.presentation.controller.dto.request.ReqUserNicknameDto;
import demo.eternalreturn.presentation.controller.dto.request.ReqExperimentCodeDto;

public interface CustomService {

    ResponseDto<?> searchUserInfoByUsername(ReqUserNicknameDto userNicknameDto);

    ResponseDto<?> searchExperimentStatByExperimentCode(ReqExperimentCodeDto experimentCodeDto);

}
