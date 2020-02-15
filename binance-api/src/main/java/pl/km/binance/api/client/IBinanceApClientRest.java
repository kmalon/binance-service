package pl.km.binance.api.client;

import feign.Headers;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.km.binance.api.domain.response.account.AccountInfo;
import pl.km.binance.api.domain.response.account.Trades;
import pl.km.binance.api.domain.response.general.ExchangeInfo;
import pl.km.binance.api.domain.response.general.ServerTime;

import java.util.List;
import java.util.Map;

/**
 * Binance REST API client for public endpoints
 * https://github.com/binance-exchange/binance-official-api-docs/blob/master/rest-api.md
 */
@Headers({"Content-type", "application/json"})
interface IBinanceApClientRest {
    /**
     * Test conectivity of the REST API
     *
     * @return
     */
    @RequestMapping(value = BinanceApiRestMappings.PING, method = RequestMethod.GET)
    ResponseEntity<Object> ping();

    @RequestMapping(value = BinanceApiRestMappings.SERVER_TIME, method = RequestMethod.GET)
    ResponseEntity<ServerTime> serverTime();

    @RequestMapping(value = BinanceApiRestMappings.EXCHANGE_INFO, method = RequestMethod.GET)
    ResponseEntity<ExchangeInfo> exchangeInfo();

    @RequestMapping(value = BinanceApiRestMappings.ACCOUNT_INFORMATIONS, method = RequestMethod.GET)
    ResponseEntity<AccountInfo> getAccountInfo(@SpringQueryMap Map<String, ?> params, @RequestHeader(value = BinanceApiHeaders.X_MBX_APIKEY) String apiKey);

    @RequestMapping(value = BinanceApiRestMappings.MY_TRADES, method = RequestMethod.GET)
    ResponseEntity<List<Trades>> getUserTrades(@SpringQueryMap Map<String, ?> params, @RequestHeader(value = BinanceApiHeaders.X_MBX_APIKEY) String apiKey);

    @RequestMapping(value = BinanceApiRestMappings.ALL_ORDERS_LIST, method = RequestMethod.GET)
    ResponseEntity<List<String>> getAllOkoOrderList(@SpringQueryMap Map<String, ?> params, @RequestHeader(value = BinanceApiHeaders.X_MBX_APIKEY) String apiKey);
}