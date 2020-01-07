package pl.km.binance.api.infrastructure;

import feign.Headers;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.km.binance.api.domain.constants.BinanceApiHeaders;
import pl.km.binance.api.domain.constants.BinanceApiRestMappings;
import pl.km.binance.api.domain.exchange.account.AccountInfo;
import pl.km.binance.api.domain.exchange.account.Trades;
import pl.km.binance.api.domain.exchange.general.ExchangeInfo;
import pl.km.binance.api.domain.exchange.general.ServerTime;

import java.util.List;
import java.util.Map;

/**
 * Binance REST API client for public endpoints
 * https://github.com/binance-exchange/binance-official-api-docs/blob/master/rest-api.md
 */
interface BinanceApiRestFeignClient {
    /**
     * Test conectivity of the REST API
     *
     * @return
     */
    @Headers("Content-Type: application/json")
    @RequestMapping(value = BinanceApiRestMappings.PING, method = RequestMethod.GET)
    ResponseEntity<Object> ping();

    @Headers("Content-Type: application/json")
    @RequestMapping(value = BinanceApiRestMappings.SERVER_TIME, method = RequestMethod.GET)
    ServerTime serverTime();

    @Headers("Content-Type: application/json")
    @RequestMapping(value = BinanceApiRestMappings.EXCHANGE_INFO, method = RequestMethod.GET)
    ExchangeInfo exchangeInfo();

    @Headers("Content-Type: application/json")
    @RequestMapping(value = BinanceApiRestMappings.ACCOUNT_INFORMATIONS, method = RequestMethod.GET)
    AccountInfo getAccountInfo(@SpringQueryMap Map<String, ?> params, @RequestHeader(value = BinanceApiHeaders.X_MBX_APIKEY) String apiKey);

    @Headers("Content-Type: application/json")
    @RequestMapping(value = BinanceApiRestMappings.MY_TRADES, method = RequestMethod.GET)
    List<Trades> getUserTrades(@SpringQueryMap Map<String, ?> params, @RequestHeader(value = BinanceApiHeaders.X_MBX_APIKEY) String apiKey);
}