package pl.km.client.domain.binance.config;

import lombok.Getter;
import pl.km.client.domain.common.ExchangeInfo;
import pl.km.client.domain.port.outgoing.IBinanceApi;

public class BinanceApiTest implements IBinanceApi {

    private boolean failFirstInvoke;

    @Getter
    private int exchangeInfoInvokeCounter = 0;

    public BinanceApiTest(boolean failFirstInvoke) {
        this.failFirstInvoke = failFirstInvoke;
    }

    @Override
    public ExchangeInfo getExchangeInfo() {
        if (failFirstInvoke) {
            failFirstInvoke = false;
            throw new RuntimeException("Invoke Fail");
        }
        exchangeInfoInvokeCounter++;
        return ExchangeInfo.builder().serverTime(exchangeInfoInvokeCounter).build();
    }
}