package pl.km.binance.api.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.km.binance.api.domain.exchange.account.AccountInfo;
import pl.km.binance.api.domain.exchange.account.Trades;
import pl.km.binance.api.domain.exchange.general.ExchangeInfo;
import pl.km.binance.api.domain.exchange.general.ServerTime;
import pl.km.binance.api.domain.request.AccountRequest;
import pl.km.binance.api.domain.request.MyTradesRequest;
import pl.km.binance.api.domain.security.ISecretKey;
import pl.km.binance.api.domain.time.BinanceServerTime;
import pl.km.binance.api.domain.time.IBinanceTime;
import pl.km.binance.api.infrastructure.IBinanceApClientRest;

import java.util.List;

public class BinanceApiRestClient implements IBinanceApiRest {

    private IBinanceApClientRest binanceApClient;
    private IBinanceTime binanceServerTime;

    public BinanceApiRestClient(String binanceBaseUrl, int connectTimeoutMillis, int readTimeoutMillis) {
        this.binanceApClient = IBinanceApClientRest.newInstance(binanceBaseUrl, connectTimeoutMillis, readTimeoutMillis);
        this.binanceServerTime = new BinanceServerTime(this);
    }

    @Override
    public ResponseEntity<Void> ping() {
        return binanceApClient.ping().getStatusCode().is2xxSuccessful() ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

    @Override
    public ResponseEntity<ServerTime> serverTime() {
        return binanceApClient.serverTime();
    }

    @Override
    public ResponseEntity<ExchangeInfo> exchangeInfo() {
        return binanceApClient.exchangeInfo();
    }

    @Override
    public ResponseEntity<AccountInfo> getAccountInfo(ISecretKey binanceSecretKey, String apiKey, AccountRequest accountRequest) {
        return binanceApClient.getAccountInfo(accountRequest.getParamsWithSignature(binanceSecretKey, binanceServerTime), apiKey);
    }

    @Override
    public ResponseEntity<List<Trades>> getUserTrades(ISecretKey binanceSecretKey, String apiKey, MyTradesRequest myTradesRequest) {
        return binanceApClient.getUserTrades(myTradesRequest.getParamsWithSignature(binanceSecretKey, binanceServerTime), apiKey);
    }
}