package pl.km.binance.api.domain.time;

import pl.km.binance.api.infrastructure.BinanceApiRest;

/**
 * Time of Binance Server
 */
public class BinanceServerTime implements BinanceTime {
    /**
     * Difference between the Binance API server time and local time
     */
    private long timeDifference;
    /**
     * Binance REST API client for time synchronization
     */
    private BinanceApiRest binanceApiRest;

    public BinanceServerTime(BinanceApiRest binanceApiRest) {
        this.binanceApiRest = binanceApiRest;
        synchronizeBinanceTime();
    }

    /**
     * Binance API REST server time computed base on local system time and time difference between Binance server
     *
     * @return millis of Binance server
     */
    public long getBinanceTime() {
        return System.currentTimeMillis() + timeDifference;
    }

    /**
     * Synchronizing the Binance API server time by computing difference between local and Binance time
     */
    public void synchronizeBinanceTime() {
        this.timeDifference = System.currentTimeMillis() - binanceApiRest.serverTime().getServerTime();
    }


}