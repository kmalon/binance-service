package pl.km.binance.api.domain.response.account;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Balance {
    private String asset;
    private BigDecimal free;
    private BigDecimal locked;
}
