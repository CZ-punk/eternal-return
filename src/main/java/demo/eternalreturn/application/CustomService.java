package demo.eternalreturn.application;

import demo.eternalreturn.infrastructure.proxy.dto.request.ReqUserNicknameDto;
import org.springframework.http.ResponseEntity;

public interface CustomService {
    ResponseEntity<?> searchUserInfoByUsername(ReqUserNicknameDto userNicknameDto);

}
