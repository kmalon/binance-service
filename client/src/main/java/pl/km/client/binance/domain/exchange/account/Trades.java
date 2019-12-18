package pl.km.client.binance.domain.exchange.account;

import lombok.Value;
import pl.km.client.binance.domain.exchange.general.Symbol;

import java.math.BigDecimal;

@Value
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
