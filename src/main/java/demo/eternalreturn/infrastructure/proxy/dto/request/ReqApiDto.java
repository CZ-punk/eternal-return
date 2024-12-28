package demo.eternalreturn.infrastructure.proxy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqApiDto {

    private String pathVariable;
    private Map<String, String> queryParams;
    private Object requestBody;
    private HttpMethod method;

}
