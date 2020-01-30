package pl.km.binance.api.infrastructure;

import org.springframework.http.ResponseEntity;
import pl.km.binance.api.domain.exchange.account.AccountInfo;
import pl.km.binance.api.domain.exchange.account.Trades;
import pl.km.binance.api.domain.exchange.general.ExchangeInfo;
import pl.km.binance.api.domain.exchange.general.ServerTime;
import pl.km.binance.api.domain.request.secured.ISecuredRequestQueryParams;
import pl.km.binance.api.domain.security.ISecretKey;

import java.util.List;

public interface BinanceApiRest {
    ResponseEntity<Void> ping();

    ResponseEntity<ServerTime> serverTime();

    ResponseEntity<ExchangeInfo> exchangeInfo();

    ResponseEntity<AccountInfo> getAccountInfo(ISecretKey binanceSecretKey, String apiKey, ISecuredRequestQueryParams accountRequest);

    ResponseEntity<List<Trades>> getUserTrades(ISecretKey binanceSecretKey, String apiKey, ISecuredRequestQueryParams myTradesRequest);
}