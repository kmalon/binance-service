package pl.km.binance.api.client;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.Request;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

class RestClientFactory {
    static IBinanceApClientRest newInstance(String binanceBaseUrl, int connectTimeoutMillis, int readTimeoutMillis) {
        final var executionTimeoutSetter = HystrixCommandProperties.defaultSetter()
                .withExecutionTimeoutInMilliseconds(readTimeoutMillis);
        return HystrixFeign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new ResponseEntityDecoder(new JacksonDecoder()))
                .contract(new SpringMvcContract())
                .logger(new Slf4jLogger())
                .setterFactory((target1, method) -> HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey("binance"))
                        .andCommandPropertiesDefaults(executionTimeoutSetter))
                .errorDecoder(new BinanceErrorDecoder())
                .options(new Request.Options(connectTimeoutMillis, readTimeoutMillis))
                .target(IBinanceApClientRest.class, binanceBaseUrl);
    }
}