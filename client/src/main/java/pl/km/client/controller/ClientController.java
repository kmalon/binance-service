package pl.km.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.km.binance.api.domain.exchange.account.AccountInfo;
import pl.km.binance.api.domain.exchange.account.Trades;
import pl.km.binance.api.domain.exchange.general.ExchangeInfo;
import pl.km.binance.api.domain.exchange.general.ServerTime;
import pl.km.binance.api.domain.request.AccountRequest;
import pl.km.binance.api.domain.request.MyTradesRequest;
import pl.km.binance.api.domain.security.BinanceSecretKey;
import pl.km.binance.api.infrastructure.BinanceApiRest;
import pl.km.binance.api.infrastructure.BinanceApiRestClient;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/public")
public class ClientController {

    private BinanceApiRest binanceApiRest;

    public ClientController(@Value("${binance.api.base-url}${binance.api.uri-prefix-with-version}") String binanceBaseUrl) {
        this.binanceApiRest = new BinanceApiRestClient(binanceBaseUrl,6000,6000);
    }

    @GetMapping(path = "/noauth")
    public ResponseEntity<AccountInfo> test(@RequestParam("api-key") String key, @RequestParam("sec-key") char[] seckey) {
        log.info("User not authenticated");
        BinanceSecretKey binanceSecretKey = new BinanceSecretKey(seckey);
        ResponseEntity<Void> ping = binanceApiRest.ping();
        ResponseEntity<ServerTime> objectResponseEntity = binanceApiRest.serverTime();
        ResponseEntity<ExchangeInfo> exchangeInfoResponseEntity = binanceApiRest.exchangeInfo();
        ResponseEntity<AccountInfo> accountInfo = binanceApiRest.getAccountInfo(binanceSecretKey, key, new AccountRequest());
        ResponseEntity<List<Trades>> userTrades = binanceApiRest.getUserTrades(binanceSecretKey, key, new MyTradesRequest("ETHBTC"));
        binanceSecretKey.destroy();

        return accountInfo;
//        return ResponseEntity.ok("Not authenticated");
    }
}