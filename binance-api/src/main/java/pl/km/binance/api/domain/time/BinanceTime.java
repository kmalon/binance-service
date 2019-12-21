package pl.km.binance.api.domain.time;

public interface BinanceTime {
    long getBinanceTime();

    void synchronizeBinanceTime();
}
