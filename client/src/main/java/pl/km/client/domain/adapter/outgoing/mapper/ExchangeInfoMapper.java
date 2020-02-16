package pl.km.client.domain.adapter.outgoing.mapper;

import pl.km.client.domain.common.ExchangeInfo;

public class ExchangeInfoMapper {
    public static ExchangeInfo from(pl.km.binance.api.domain.response.general.ExchangeInfo exchangeInfo) {
        return ExchangeInfo.builder()
                .timezone(exchangeInfo.getTimezone())
                .serverTime(exchangeInfo.getServerTime())
                .symbols(SymbolExchangeMapper.from(exchangeInfo.getSymbols()))
                .build();
    }


}
