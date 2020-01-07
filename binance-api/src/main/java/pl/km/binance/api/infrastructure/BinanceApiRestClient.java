package pl.km.binance.api.infrastructure;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import pl.km.binance.api.domain.error.BinanceErrorDecoder;
import pl.km.binance.api.domain.exchange.account.AccountInfo;
import pl.km.binance.api.domain.exchange.account.Trades;
import pl.km.binance.api.domain.exchange.general.ExchangeInfo;
import pl.km.binance.api.domain.exchange.general.ServerTime;
import pl.km.binance.api.domain.request.AccountRequest;
import pl.km.binance.api.domain.request.MyTradesRequest;
import pl.km.binance.api.domain.security.BinanceSecretKey;
import pl.km.binance.api.domain.time.BinanceServerTime;

import java.util.List;

public class BinanceApiRestClient implements BinanceApiRest {

    private BinanceApiRestFeignClient binanceApiRestFeignClient;
    private BinanceServerTime binanceServerTime;

    public BinanceApiRestClient(String binanceBaseUrl) {
        this.binanceApiRestFeignClient = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .logger(new Slf4jLogger())
                .errorDecoder(new BinanceErrorDecoder())
                .target(BinanceApiRestFeignClient.class, binanceBaseUrl);
        this.binanceServerTime = new BinanceServerTime(this);
    }

    @Override
    public boolean ping() {
        return binanceApiRestFeignClient.ping().getStatusCode().is2xxSuccessful();
    }

    @Override
    public ServerTime serverTime() {
        return binanceApiRestFeignClient.serverTime();
    }

    @Override
    public ExchangeInfo exchangeInfo() {
        return binanceApiRestFeignClient.exchangeInfo();
    }

    @Override
    public AccountInfo getAccountInfo(BinanceSecretKey binanceSecretKey, String apiKey, AccountRequest accountRequest) {
        return binanceApiRestFeignClient.getAccountInfo(accountRequest.getParamsWithSignatureAndForCurrentTime(binanceSecretKey, binanceServerTime), apiKey);
    }

    @Override
    public List<Trades> getUserTrades(BinanceSecretKey binanceSecretKey, String apiKey, MyTradesRequest myTradesRequest) {
        return binanceApiRestFeignClient.getUserTrades(myTradesRequest.getParamsWithSignatureAndForCurrentTime(binanceSecretKey, binanceServerTime), apiKey);
    }
}