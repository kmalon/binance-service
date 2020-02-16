package pl.km.client.domain.common;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ExchangeInfo {
    private String timezone;
    private long serverTime;
    private List<SymbolExchange> symbols;
}