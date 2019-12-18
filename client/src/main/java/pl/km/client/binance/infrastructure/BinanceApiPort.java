package pl.km.client.binance.infrastructure;

import pl.km.client.binance.domain.exchange.account.AccountInfo;
import pl.km.client.binance.domain.exchange.account.Trades;
import pl.km.client.binance.domain.exchange.general.ExchangeInfo;
import pl.km.client.binance.domain.exchange.general.ServerTime;
import pl.km.client.binance.domain.security.BinanceSecretKey;

import java.util.List;

public interface BinanceApiPort {
    boolean ping();

    ServerTime serverTime();

    ExchangeInfo exchangeInfo();

    AccountInfo getAccountInfo(BinanceSecretKey binanceSecretKey, String apiKey);

    List<Trades> getUserTrades(BinanceSecretKey binanceSecretKey, String apiKey);
}
