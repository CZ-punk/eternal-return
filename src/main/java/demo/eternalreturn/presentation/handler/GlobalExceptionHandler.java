package demo.eternalreturn.presentation.handler;

import demo.eternalreturn.infrastructure.proxy.dto.response.ResponseDto;
import demo.eternalreturn.presentation.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException e) {
        e.printStackTrace();

        return ResponseEntity.status(e.getCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseDto<>(e.getCode(), e.getResult(), e.getDescription()));
    }


}
