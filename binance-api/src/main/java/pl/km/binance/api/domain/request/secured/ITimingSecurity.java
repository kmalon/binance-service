package pl.km.binance.api.domain.request.secured;

import pl.km.binance.api.domain.time.IBinanceTime;

import java.util.LinkedHashMap;

public interface ITimingSecurity {
    /**
     * @param binanceTime
     * @return necessary time params for current time
     */
    LinkedHashMap<String, String> getTimeParamsForNow(IBinanceTime binanceTime);
}