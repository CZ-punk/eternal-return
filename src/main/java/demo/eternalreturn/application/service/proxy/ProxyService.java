package demo.eternalreturn.application.service.proxy;

import demo.eternalreturn.presentation.dto.request.eternal_return.ReqExperimentCodeDto;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqUserNicknameDto;
import demo.eternalreturn.presentation.dto.response.ResponseDto;

public interface ProxyService {

    ResponseDto<?> searchUserInfoByUsername(ReqUserNicknameDto userNicknameDto);

    ResponseDto<?> searchExperimentStatByExperimentCode(ReqExperimentCodeDto experimentCodeDto);

}
