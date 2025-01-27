package demo.eternalreturn.presentation.controller.dto.response;

import demo.eternalreturn.presentation.exception.ResultMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {

    private HttpStatusCode code;
    private ResultMessage message;
    private T data;

}
