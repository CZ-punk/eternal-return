package demo.eternalreturn.infrastructure.proxy.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqMetaDataDto {

    @NotNull
    private String metaType;
}
