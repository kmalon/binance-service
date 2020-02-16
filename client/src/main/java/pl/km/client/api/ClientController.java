package pl.km.client.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.km.binance.api.client.BinanceApiRestClient;
import pl.km.binance.api.client.IBinanceApiRest;
import pl.km.binance.api.domain.request.AccountRequest;
import pl.km.binance.api.domain.request.BinanceSecretKey;
import pl.km.binance.api.domain.request.MyTradesRequest;
import pl.km.binance.api.domain.response.account.AccountInfo;
import pl.km.binance.api.domain.response.account.Trades;
import pl.km.binance.api.domain.response.general.ExchangeInfo;
import pl.km.binance.api.domain.response.general.ServerTime;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/public")
class ClientController {

    private IBinanceApiRest IBinanceApiRest;

    ClientController(@Value("${binance.api.base-url}${binance.api.uri-prefix-with-version}") String binanceBaseUrl) {
        this.IBinanceApiRest = BinanceApiRestClient.newInstance(binanceBaseUrl, 6000, 6000);
    }

    @GetMapping(path = "/noauth")
    ResponseEntity<AccountInfo> test(@RequestParam("api-key") String key, @RequestParam("sec-key") char[] seckey) {
        log.info("User not authenticated");
        BinanceSecretKey binanceSecretKey = new BinanceSecretKey(seckey);
        ResponseEntity<Void> ping = IBinanceApiRest.ping();
        ResponseEntity<ServerTime> objectResponseEntity = IBinanceApiRest.serverTime();
        ResponseEntity<ExchangeInfo> exchangeInfoResponseEntity = IBinanceApiRest.exchangeInfo();
        ResponseEntity<AccountInfo> accountInfo = IBinanceApiRest.getAccountInfo(binanceSecretKey, key, new AccountRequest());
        ResponseEntity<List<Trades>> userTrades = IBinanceApiRest.getUserTrades(binanceSecretKey, key, new MyTradesRequest("ETHBTC"));
        binanceSecretKey.destroy();

        return accountInfo;
//        return ResponseEntity.ok("Not authenticated");
    }
}