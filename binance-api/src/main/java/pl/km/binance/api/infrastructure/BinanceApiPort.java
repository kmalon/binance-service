package pl.km.binance.api.infrastructure;

import pl.km.binance.api.domain.exchange.account.AccountInfo;
import pl.km.binance.api.domain.exchange.account.Trades;
import pl.km.binance.api.domain.exchange.general.ExchangeInfo;
import pl.km.binance.api.domain.exchange.general.ServerTime;
import pl.km.binance.api.domain.security.BinanceSecretKey;

import java.util.List;

public interface BinanceApiPort {
    boolean ping();

    ServerTime serverTime();

    ExchangeInfo exchangeInfo();

    AccountInfo getAccountInfo(BinanceSecretKey binanceSecretKey, String apiKey);

    List<Trades> getUserTrades(BinanceSecretKey binanceSecretKey, String apiKey);
}
