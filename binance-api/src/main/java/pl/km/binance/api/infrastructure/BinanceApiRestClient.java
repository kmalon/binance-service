package pl.km.binance.api.infrastructure;

import feign.Request;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.km.binance.api.domain.error.binance.server.BinanceErrorDecoder;
import pl.km.binance.api.domain.exchange.account.AccountInfo;
import pl.km.binance.api.domain.exchange.account.Trades;
import pl.km.binance.api.domain.exchange.general.ExchangeInfo;
import pl.km.binance.api.domain.exchange.general.ServerTime;
import pl.km.binance.api.domain.request.secured.ISecuredRequestQueryParams;
import pl.km.binance.api.domain.security.ISecretKey;
import pl.km.binance.api.domain.time.BinanceServerTime;
import pl.km.binance.api.infrastructure.config.HystrixBinanceFallbackFactory;

import java.util.List;

public class BinanceApiRestClient implements BinanceApiRest {

    private BinanceApiRestFeignClient binanceApiRestFeignClient;
    private BinanceServerTime binanceServerTime;

    public BinanceApiRestClient(String binanceBaseUrl, int connectTimeoutMillis, int readTimeoutMillis) {
        this.binanceApiRestFeignClient = HystrixFeign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new ResponseEntityDecoder(new JacksonDecoder()))
                .contract(new SpringMvcContract())
                .logger(new Slf4jLogger())
                .errorDecoder(new BinanceErrorDecoder())
                .options(new Request.Options(connectTimeoutMillis, readTimeoutMillis))
                .target(BinanceApiRestFeignClient.class, binanceBaseUrl, new HystrixBinanceFallbackFactory());
        this.binanceServerTime = new BinanceServerTime(this);
    }

    @Override
    public ResponseEntity<Void> ping() {
        return binanceApiRestFeignClient.ping().getStatusCode().is2xxSuccessful() ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

    @Override
    public ResponseEntity<ServerTime> serverTime() {
        return binanceApiRestFeignClient.serverTime();
    }

    @Override
    public ResponseEntity<ExchangeInfo> exchangeInfo() {
        return binanceApiRestFeignClient.exchangeInfo();
    }

    @Override
    public ResponseEntity<AccountInfo> getAccountInfo(ISecretKey binanceSecretKey, String apiKey, ISecuredRequestQueryParams accountRequest) {
        return binanceApiRestFeignClient.getAccountInfo(accountRequest.getParamsWithSignature(binanceSecretKey, binanceServerTime), apiKey);
    }

    @Override
    public ResponseEntity<List<Trades>> getUserTrades(ISecretKey binanceSecretKey, String apiKey, ISecuredRequestQueryParams myTradesRequest) {
        return binanceApiRestFeignClient.getUserTrades(myTradesRequest.getParamsWithSignature(binanceSecretKey, binanceServerTime), apiKey);
    }
}