package pl.km.binance.api.domain.exchange.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.util.List;

/**
 * Exchange Symbol information
 * https://github.com/binance-exchange/binance-official-api-docs/blob/master/rest-api.md#exchange-information
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolExchange {
    private Symbol symbol;
    private SybmolStatus status;
    private String baseAsset;
    private int baseAssetPrecision;
    private String quoteAsset;
    private int quotePrecision;
    private String baseCommissionPrecision;
    private String quoteCommissionPrecision;
    private List<OrderType> orderTypes;
    private boolean icebergAllowed;
    private boolean ocoAllowed;
    private boolean quoteOrderQtyMarketAllowed;
    private boolean isSpotTradingAllowed;
    private boolean isMarginTradingAllowed;
//    todo filters

}