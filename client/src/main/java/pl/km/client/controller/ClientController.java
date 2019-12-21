package pl.km.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.km.binance.api.domain.exchange.account.Trades;
import pl.km.binance.api.domain.security.BinanceSecretKey;
import pl.km.binance.api.infrastructure.BinanceApiRestClientAdapter;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/public")
public class ClientController {

    private pl.km.binance.api.infrastructure.BinanceApiPort binanceApiPort;

    public ClientController() {
        this.binanceApiPort = new BinanceApiRestClientAdapter("https://api.binance.com/api/v3");
    }

    @GetMapping(path = "/noauth")
    public ResponseEntity<String> test(@RequestParam("api-key") String key, @RequestParam("sec-key") char[] seckey) {
        log.info("User not authenticated");
//        ExchangeInfo exchangeInfo = binanceApiPort.exchangeInfo();
//        AccountInfo accountInfo = binanceApiPort.getAccountInfo(new BinanceSecretKey(seckey), key);
        List<Trades> userTrades = binanceApiPort.getUserTrades(new BinanceSecretKey(seckey), key);
        return ResponseEntity.ok("Not authenticated");
    }
}