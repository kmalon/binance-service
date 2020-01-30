package pl.km.binance.api.domain.time;

public interface IBinanceTime {
    long getBinanceTime();

    void synchronizeBinanceTime();
}
