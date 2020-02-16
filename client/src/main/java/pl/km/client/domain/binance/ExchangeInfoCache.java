package pl.km.client.domain.binance;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import pl.km.client.domain.common.Constants;
import pl.km.client.domain.common.ExchangeInfo;
import pl.km.client.domain.port.outgoing.IBinanceApi;

import java.util.Objects;

/**
 * Simple exchange info cache, can be manually synchronized.
 * Returns once gathered information unless is manually synchronized.
 */
@Slf4j
class ExchangeInfoCache {
    private IBinanceApi binanceApi;
    private ExchangeInfo exchangeInfo;


    ExchangeInfoCache(IBinanceApi binanceApi) {
        this.binanceApi = binanceApi;
        initExchangeInfo();
    }

    private void initExchangeInfo() {
        try {
            exchangeInfo = binanceApi.getExchangeInfo();
        } catch (Exception e) {
            log.error(String.format(Constants.Errors.BINANCE_CONNECTION_ERROR, "ExchangeInfo"));
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }

    ExchangeInfo getExchangeInfo() {
        if (Objects.isNull(exchangeInfo)) {
            this.synchronizeExchangeInfo();
        }
        return exchangeInfo;
    }

    void synchronizeExchangeInfo() {
        exchangeInfo = binanceApi.getExchangeInfo();
    }
}