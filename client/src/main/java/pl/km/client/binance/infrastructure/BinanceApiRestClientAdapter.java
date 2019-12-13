package pl.km.client.binance.infrastructure;

import org.springframework.stereotype.Service;
import pl.km.client.binance.domain.exchange.account.AccountInfo;
import pl.km.client.binance.domain.exchange.general.ExchangeInfo;
import pl.km.client.binance.domain.exchange.general.ServerTime;
import pl.km.client.binance.domain.request.AccountRequest;
import pl.km.client.binance.domain.security.BinanceSecretKey;

@Service
public class BinanceApiRestClientAdapter implements BinanceApiPort {

    private IBinanceApiRestClient iBinanceApiRestClient;

    public BinanceApiRestClientAdapter(IBinanceApiRestClient iBinanceApiRestClient) {
        this.iBinanceApiRestClient = iBinanceApiRestClient;
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
}