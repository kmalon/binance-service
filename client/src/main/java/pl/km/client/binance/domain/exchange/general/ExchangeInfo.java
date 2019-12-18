package pl.km.client.binance.domain.exchange.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Value;

import java.util.List;

/**
 * Current exchange trading rules and symbol information
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeInfo {
    private String timezone;
    private long serverTime;
    private List<RateLimit> rateLimits;
    //  TODO  exchange filters
    private List<SymbolExchange> symbols;

}