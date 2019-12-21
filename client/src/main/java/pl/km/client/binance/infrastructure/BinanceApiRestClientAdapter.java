package pl.km.client.binance.infrastructure;

import feign.Feign;
import org.springframework.stereotype.Service;
import pl.km.client.binance.domain.exchange.account.AccountInfo;
import pl.km.client.binance.domain.exchange.account.Trades;
import pl.km.client.binance.domain.exchange.general.ExchangeInfo;
import pl.km.client.binance.domain.exchange.general.ServerTime;
import pl.km.client.binance.domain.exchange.requests.AccountRequest;
import pl.km.client.binance.domain.exchange.requests.MyTradesRequest;
import pl.km.client.binance.domain.security.BinanceSecretKey;

import java.util.List;

@Service
public class BinanceApiRestClientAdapter implements BinanceApiPort {

    private IBinanceApiRestClient iBinanceApiRestClient;

    public BinanceApiRestClientAdapter() {
//        this.iBinanceApiRestClient = Feign.builder()
//                .target(IBinanceApiRestClient.class, "");
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
        accountRequest.addSignature(binanceSecretKey);
        return iBinanceApiRestClient.getAccountInfo(accountRequest.getParams(), apiKey);
    }

    @Override
    public List<Trades> getUserTrades(BinanceSecretKey binanceSecretKey, String apiKey) {
        long serverTime = iBinanceApiRestClient.serverTime().getServerTime();
        MyTradesRequest myTradesRequest = new MyTradesRequest(serverTime, 60000, "ETHBTC");
        myTradesRequest.addSignature(binanceSecretKey);
        return iBinanceApiRestClient.getUserTrades(myTradesRequest.getParams(), apiKey);
    }
}