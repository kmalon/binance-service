package pl.km.client.domain.adapter.outgoing.mapper;

import pl.km.client.domain.common.SymbolExchange;

import java.util.List;
import java.util.stream.Collectors;

class SymbolExchangeMapper {
    static List<SymbolExchange> from(List<pl.km.binance.api.domain.response.general.SymbolExchange> symbolExchanges) {
        return symbolExchanges.stream().map(SymbolExchangeMapper::from).collect(Collectors.toList());
    }

    private static SymbolExchange from(pl.km.binance.api.domain.response.general.SymbolExchange symbolExchange) {
        return SymbolExchange.builder()
                .symbol(symbolExchange.getSymbol())
                .status(symbolExchange.getStatus())
                .baseAsset(symbolExchange.getBaseAsset())
                .baseAssetPrecision(symbolExchange.getBaseAssetPrecision())
                .quoteAsset(symbolExchange.getQuoteAsset())
                .quotePrecision(symbolExchange.getQuotePrecision())
                .build();
    }
}
