package pl.km.binance.api.domain.response.account;

import lombok.Data;
import pl.km.binance.api.domain.response.general.Symbol;

import java.math.BigDecimal;

@Data
public class Trades {
    private Symbol symbol;
    private int id;
    private int orderId;
    private int orderListId;
    private BigDecimal price;
    private BigDecimal qty;
    private BigDecimal quoteQty;
    private BigDecimal commission;
    private String commissionAsset;
    private long time;
    private boolean isBuyer;
    private boolean isMaker;
    private boolean isBestMatch;
}
