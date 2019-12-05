package pl.km.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.km.client.binance.domain.exchange.common.ExchangeInfo;
import pl.km.client.binance.infrastructure.rest.BinanceApiPublicRestClient;

@Slf4j
@RestController
@RequestMapping("/public")
public class ClientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    private BinanceApiPublicRestClient binanceApiPublicRestClient;

    public ClientController(BinanceApiPublicRestClient binanceApiPublicRestClient) {
        this.binanceApiPublicRestClient = binanceApiPublicRestClient;
    }

    @GetMapping(path = "/noauth")
    public ResponseEntity test() {
        LOGGER.info("User not authenticated");
        ExchangeInfo exchangeInfo = binanceApiPublicRestClient.exchangeInfo();
        LOGGER.info("bla bla bla");
        return ResponseEntity.ok("Not authenticated");
    }
}