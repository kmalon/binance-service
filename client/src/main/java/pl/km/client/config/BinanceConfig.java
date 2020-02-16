package pl.km.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.km.binance.api.client.BinanceApiRestClient;
import pl.km.client.domain.adapter.outgoing.BinanceApiRest;
import pl.km.client.domain.port.outgoing.IBinanceApi;

@Configuration
public class BinanceConfig {

    @Bean
    IBinanceApi iBinanceApi(@Value("${binance.api.base-url}${binance.api.uri-prefix-with-version}") String binanceBaseUrl,
                            @Value("${binance.api.connection-timeout-millis}") int connectionTimeOutMillis,
                            @Value("${binance.api.read-timeout-millis}") int readTimeOutMillis) {
        return new BinanceApiRest(BinanceApiRestClient.newInstance(binanceBaseUrl, connectionTimeOutMillis, readTimeOutMillis));
    }

}