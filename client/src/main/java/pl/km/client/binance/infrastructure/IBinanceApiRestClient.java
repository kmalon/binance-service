package pl.km.client.binance.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.km.client.binance.domain.constants.BinanceApiHeaders;
import pl.km.client.binance.domain.constants.BinanceApiRestMappings;
import pl.km.client.binance.domain.exchange.account.AccountInfo;
import pl.km.client.binance.domain.exchange.account.Trades;
import pl.km.client.binance.domain.exchange.general.ExchangeInfo;
import pl.km.client.binance.domain.exchange.general.ServerTime;

import java.util.List;
import java.util.Map;

/**
 * Binance REST API client for public endpoints
 * https://github.com/binance-exchange/binance-official-api-docs/blob/master/rest-api.md
 */
@Component
@FeignClient(url = "${binance.api.base-url}${binance.api.uri-prefix-with-version}", name = "BinanceApiPublicRestClient")
interface IBinanceApiRestClient {
    /**
     * Test conectivity of the REST API
     *
     * @return
     */
    @RequestMapping(value = BinanceApiRestMappings.PING, method = RequestMethod.GET)
    ResponseEntity<Object> ping();

    @RequestMapping(value = BinanceApiRestMappings.SERVER_TIME, method = RequestMethod.GET)
    ServerTime serverTime();

    @RequestMapping(value = BinanceApiRestMappings.EXCHANGE_INFO, method = RequestMethod.GET)
    ExchangeInfo exchangeInfo();

    @RequestMapping(value = BinanceApiRestMappings.ACCOUNT_INFORMATIONS, method = RequestMethod.GET)
    AccountInfo getAccountInfo(@SpringQueryMap Map<String, ?> params, @RequestHeader(value = BinanceApiHeaders.X_MBX_APIKEY) String apiKey);

    @RequestMapping(value = BinanceApiRestMappings.MY_TRADES, method = RequestMethod.GET)
    List<Trades> getUserTrades(@SpringQueryMap Map<String, ?> params, @RequestHeader(value = BinanceApiHeaders.X_MBX_APIKEY) String apiKey);
}