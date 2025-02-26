package demo.eternalreturn.presentation.exception;

import demo.eternalreturn.presentation.dto.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class CustomException extends RuntimeException {

    private HttpStatusCode code;
    private ResultMessage result;
    private String message;

    public CustomException(HttpStatusCode statusCode, String message) {
        code = statusCode;
        result = ResultMessage.Error;
        this.message = message;
    }

    public ResponseDto<?> toResponseDto() {
        return new ResponseDto<>(code, result, message);
    }

}
