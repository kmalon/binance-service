package pl.km.binance.api.infrastructure;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.Headers;
import feign.Request;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.km.binance.api.domain.constants.BinanceApiHeaders;
import pl.km.binance.api.domain.constants.BinanceApiRestMappings;
import pl.km.binance.api.domain.error.binance.server.BinanceErrorDecoder;
import pl.km.binance.api.domain.exchange.account.AccountInfo;
import pl.km.binance.api.domain.exchange.account.Trades;
import pl.km.binance.api.domain.exchange.general.ExchangeInfo;
import pl.km.binance.api.domain.exchange.general.ServerTime;
import pl.km.binance.api.infrastructure.config.HystrixBinanceFallbackFactory;

import java.util.List;
import java.util.Map;

/**
 * Binance REST API client for public endpoints
 * https://github.com/binance-exchange/binance-official-api-docs/blob/master/rest-api.md
 */
@Headers({"Content-type", "application/json"})
public interface IBinanceApClientRest {

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
                .target(IBinanceApClientRest.class, binanceBaseUrl, new HystrixBinanceFallbackFactory());
    }

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
}