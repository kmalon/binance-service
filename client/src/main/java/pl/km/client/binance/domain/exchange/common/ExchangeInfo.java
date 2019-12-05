package pl.km.client.binance.domain.exchange.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeInfo {
    private List<Symbol> symbols;
}