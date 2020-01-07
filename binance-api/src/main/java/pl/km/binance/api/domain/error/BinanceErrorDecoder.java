package pl.km.binance.api.domain.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;

public class BinanceErrorDecoder implements ErrorDecoder {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    @SneakyThrows
    @Override
    public Exception decode(String methodKey, Response response) {
        String responseBody = Util.toString(response.body().asReader());
        ErrorResponse errorResponse = OBJECT_MAPPER.readValue(responseBody, ErrorResponse.class);
        return new BinanceInvokeError(errorResponse.getErrorMessage());
    }
}
