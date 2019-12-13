package pl.km.client.binance.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.km.client.binance.domain.constants.BinanceApiHeaders;
import pl.km.client.binance.domain.constants.BinanceApiRestMappings;
import pl.km.client.binance.domain.exchange.account.AccountInfo;
import pl.km.client.binance.domain.exchange.general.ExchangeInfo;
import pl.km.client.binance.domain.exchange.general.ServerTime;

import java.util.Map;

/**
 * Binance REST API client for public endpoints
 * https://github.com/binance-exchange/binance-official-api-docs/blob/master/rest-api.md
 */
@Component
@FeignClient(url = "${binance.api.base-url}${binance.api.uri-prefix-with-version}", name = "BinanceApiPublicRestClient")
public interface IBinanceApiRestClient {
    /**
     * Test conectivity of the REST API
     *
     * @return
     */
    @RequestMapping(BinanceApiRestMappings.PING)
    ResponseEntity<Object> ping();

    @RequestMapping(BinanceApiRestMappings.SERVER_TIME)
    ServerTime serverTime();

    @RequestMapping(BinanceApiRestMappings.EXCHANGE_INFO)
    ExchangeInfo exchangeInfo();

    @RequestMapping(BinanceApiRestMappings.ACCOUNT_INFORMATIONS)
    AccountInfo getAccountInfo(@SpringQueryMap Map<String, ?> params, @RequestHeader(value = BinanceApiHeaders.X_MBX_APIKEY) String apiKey);
}