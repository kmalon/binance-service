package pl.km.binance.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;

class BinanceErrorDecoder implements ErrorDecoder {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    @SneakyThrows
    @Override
    public Exception decode(String methodKey, Response response) {
        var responseBody = Util.toString(response.body().asReader());
        var binanceServerErrorResponse = OBJECT_MAPPER.readValue(responseBody, BinanceServerErrorResponse.class);
        return new BinanceInvokeError(binanceServerErrorResponse.getErrorMessage());
    }
}