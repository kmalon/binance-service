package pl.km.client.domain.adapter.outgoing;

import pl.km.binance.api.client.IBinanceApiRest;
import pl.km.client.domain.adapter.outgoing.mapper.ExchangeInfoMapper;
import pl.km.client.domain.common.ExchangeInfo;
import pl.km.client.domain.port.outgoing.IBinanceApi;

public class BinanceApiRest implements IBinanceApi {

    IBinanceApiRest iBinanceApiRest;

    public BinanceApiRest(IBinanceApiRest iBinanceApiRest) {
        this.iBinanceApiRest = iBinanceApiRest;
    }

    @Override
    public ExchangeInfo getExchangeInfo() {
        return ExchangeInfoMapper.from(iBinanceApiRest.exchangeInfo().getBody());
    }
}