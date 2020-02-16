package pl.km.client.domain.binance;

import pl.km.client.domain.port.incoming.IExchangeFacade;
import pl.km.client.domain.common.ExchangeInfo;
import pl.km.client.domain.port.outgoing.IBinanceApi;

public class ExchangeFacade implements IExchangeFacade {

    private IBinanceApi binanceApi;
    private ExchangeInfoCache exchangeInfoCache;

    public ExchangeFacade(IBinanceApi binanceApi) {
        this.binanceApi = binanceApi;
        this.exchangeInfoCache = new ExchangeInfoCache(binanceApi);
    }

    public ExchangeInfo getExchangeInfo() {
        return exchangeInfoCache.getExchangeInfo();
    }

    public void synchronizeExchangeInfo() {
        exchangeInfoCache.synchronizeExchangeInfo();
    }

}