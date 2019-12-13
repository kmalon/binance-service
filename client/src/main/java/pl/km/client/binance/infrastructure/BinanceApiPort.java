package pl.km.client.binance.infrastructure;

import pl.km.client.binance.domain.exchange.account.AccountInfo;
import pl.km.client.binance.domain.exchange.general.ExchangeInfo;
import pl.km.client.binance.domain.exchange.general.ServerTime;
import pl.km.client.binance.domain.security.BinanceSecretKey;

public interface BinanceApiPort {
    boolean ping();

    ServerTime serverTime();

    ExchangeInfo exchangeInfo();

    AccountInfo getAccountInfo(BinanceSecretKey binanceSecretKey, String apiKey);
}
