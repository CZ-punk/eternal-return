package demo.eternalreturn.presentation.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class CustomException extends RuntimeException {

    private HttpStatusCode code;

    private ResultMessage result;

    private String description;

    public CustomException() {
        code = HttpStatus.BAD_REQUEST;
        result = ResultMessage.Error;
    }

    public CustomException(HttpStatusCode statusCode, String description) {
        code = statusCode;
        result = ResultMessage.Error;
        this.description = description;
    }

    public CustomException(HttpStatus statusCode) {
        code = statusCode;
        result = ResultMessage.Error;
    }

}
