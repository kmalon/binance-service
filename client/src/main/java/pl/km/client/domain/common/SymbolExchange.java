package pl.km.client.domain.common;

import lombok.Builder;
import lombok.Value;
import pl.km.binance.api.domain.response.general.SybmolStatus;
import pl.km.binance.api.domain.response.general.Symbol;

@Value
@Builder
public class SymbolExchange {
    private Symbol symbol;
    private SybmolStatus status;
    private String baseAsset;
    private int baseAssetPrecision;
    private String quoteAsset;
    private int quotePrecision;
}