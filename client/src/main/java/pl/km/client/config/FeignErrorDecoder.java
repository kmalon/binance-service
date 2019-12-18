package pl.km.client.config;

import feign.Response;
import feign.codec.ErrorDecoder;

//@Configuration
public class FeignErrorDecoder implements ErrorDecoder {

    //    Util.toString(body.asReader());
    @Override
    public Exception decode(String methodKey, Response response) {
        return null;
    }
}