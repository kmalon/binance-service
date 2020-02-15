package pl.km.binance.api.domain.request;

import pl.km.binance.api.client.IBinanceTime;

import java.util.LinkedHashMap;

interface ITimingSecurity {
    /**
     * @param binanceTime
     * @return necessary time params for current time
     */
    LinkedHashMap<String, String> getTimeParamsForNow(IBinanceTime binanceTime);
}