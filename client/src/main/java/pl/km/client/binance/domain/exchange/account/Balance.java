package pl.km.client.binance.domain.exchange.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Balance {
    private String asset;
    private BigDecimal free;
    private BigDecimal locked;
}
