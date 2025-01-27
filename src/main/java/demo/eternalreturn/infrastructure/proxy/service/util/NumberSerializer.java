package demo.eternalreturn.infrastructure.proxy.service.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.DecimalFormat;

@Slf4j
public class NumberSerializer extends JsonSerializer<Double> {

    @Override
    public void serialize(Double aDouble, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        DecimalFormat df = new DecimalFormat("###.###");
        double d = Double.parseDouble(df.format(aDouble));

        jsonGenerator.writeNumber(d);
    }

    @Override
    public Class<Double> handledType() {
        return Double.class; // 처리할 타입을 명시합니다.
    }
}
