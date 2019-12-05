package pl.km.client.binance.domain.exchange.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Exchange Symbol information
 * https://github.com/binance-exchange/binance-official-api-docs/blob/master/rest-api.md#exchange-information
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Symbol {
    private SymbolName symbol;
    private SybmolStatus status;
}