package pl.km.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.km.client.binance.domain.exchange.account.Trades;
import pl.km.client.binance.domain.security.BinanceSecretKey;
import pl.km.client.binance.infrastructure.BinanceApiPort;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/public")
public class ClientController {

    private BinanceApiPort binanceApiPort;

    public ClientController(BinanceApiPort binanceApiPort) {
        this.binanceApiPort = binanceApiPort;
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