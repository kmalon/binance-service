package pl.km.binance.api.domain.time;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import pl.km.binance.api.infrastructure.BinanceApiRest;

import java.util.Objects;

/**
 * Time of Binance Server
 */
@Slf4j
public class BinanceServerTime implements IBinanceTime {
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
        this.timeDifference = System.currentTimeMillis() - getServerTimeOrDefault();
    }

    private long getServerTimeOrDefault() {
        return Try.of(this::getServerTime)
                .onFailure(exc -> log.error("Cannot get Binance server time: {}", exc.getMessage()))
                .getOrElse(0L);
    }

    private long getServerTime() {
        return Objects.requireNonNull(binanceApiRest.serverTime().getBody()).getServerTime();
    }
}