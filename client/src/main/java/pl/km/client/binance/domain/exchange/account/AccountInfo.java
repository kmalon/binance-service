package pl.km.client.binance.domain.exchange.account;

import lombok.Data;

import java.util.List;

@Data
public class AccountInfo {
    private long makerCommission;
    private long takerCommission;
    private long buyerCommission;
    private long sellerCommission;
    private boolean canTrade;
    private boolean canWithdraw;
    private boolean canDeposit;
    private long updateTime;
    private String accountType;
    private List<Balance> balances;
}
