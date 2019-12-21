package pl.km.binance.api.infrastructure;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.stereotype.Service;
import pl.km.binance.api.domain.exchange.account.AccountInfo;
import pl.km.binance.api.domain.exchange.account.Trades;
import pl.km.binance.api.domain.exchange.general.ExchangeInfo;
import pl.km.binance.api.domain.exchange.general.ServerTime;
import pl.km.binance.api.domain.exchange.requests.AccountRequest;
import pl.km.binance.api.domain.exchange.requests.MyTradesRequest;
import pl.km.binance.api.domain.security.BinanceSecretKey;
import pl.km.binance.api.domain.time.BinanceServerTime;

import java.util.List;

@Service
public class BinanceApiRestClientAdapter implements BinanceApiPort {

    private IBinanceApiRestClient iBinanceApiRestClient;
    private BinanceServerTime binanceServerTime;

    public BinanceApiRestClientAdapter(String binanceUrl) {
        this.iBinanceApiRestClient = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .logLevel(Logger.Level.FULL)
                .logger(new Slf4jLogger())
                .target(IBinanceApiRestClient.class, binanceUrl);
        this.binanceServerTime = new BinanceServerTime(this);
    }

    @Override
    public boolean ping() {
        return iBinanceApiRestClient.ping().getStatusCode().is2xxSuccessful();
    }

    @Override
    public ServerTime serverTime() {
        return iBinanceApiRestClient.serverTime();
    }

    @Override
    public ExchangeInfo exchangeInfo() {
        return iBinanceApiRestClient.exchangeInfo();
    }

    @Override
    public AccountInfo getAccountInfo(BinanceSecretKey binanceSecretKey, String apiKey) {
        long serverTime = iBinanceApiRestClient.serverTime().getServerTime();
        AccountRequest accountRequest = new AccountRequest(serverTime);
//        accountRequest.addSignature(binanceSecretKey);
        return iBinanceApiRestClient.getAccountInfo(accountRequest.getParams(binanceSecretKey, binanceServerTime), apiKey);
    }

    @Override
    public List<Trades> getUserTrades(BinanceSecretKey binanceSecretKey, String apiKey) {


        long serverTime = iBinanceApiRestClient.serverTime().getServerTime();
        MyTradesRequest myTradesRequest = new MyTradesRequest(60000, "ETHBTC");
//        myTradesRequest.addSignature(binanceSecretKey);
        return iBinanceApiRestClient.getUserTrades(myTradesRequest.getParams(binanceSecretKey, binanceServerTime), apiKey);
    }
}