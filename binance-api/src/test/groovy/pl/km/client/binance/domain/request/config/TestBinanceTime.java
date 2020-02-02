package pl.km.client.binance.domain.request.config;

import pl.km.binance.api.domain.time.IBinanceTime;

public class TestBinanceTime implements IBinanceTime {

    private long binanceTimeMillis = System.currentTimeMillis();

    public void setBinanceTime(long binanceTimeMillis) {
        this.binanceTimeMillis = binanceTimeMillis;
    }

    @Override
    public long getBinanceTime() {
        return binanceTimeMillis;
    }

    @Override
    public void synchronizeBinanceTime() {
    }
}