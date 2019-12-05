package pl.km.client.binance.infrastructure.rest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.km.client.binance.domain.constants.BinanceApiRestMappings;
import pl.km.client.binance.domain.exchange.common.ExchangeInfo;

/**
 * Binance REST API client for public endpoints
 * https://github.com/binance-exchange/binance-official-api-docs/blob/master/rest-api.md
 */
@Component
@FeignClient(url = "${binance.api.base-url}${binance.api.uri-prefix-with-version}", name = "BinanceApiPublicRestClient")
public interface BinanceApiPublicRestClient {
    /**
     * Test conectivity of the REST API
     *
     * @return
     */
    @RequestMapping(BinanceApiRestMappings.PING)
    ResponseEntity<Object> ping();

    @RequestMapping(BinanceApiRestMappings.EXCHANGE_INFO)
    ExchangeInfo exchangeInfo();
}
