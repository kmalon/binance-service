package pl.km.binance.api.domain.response.account;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public void setBalances(List<Balance> balances) {
        this.balances = balances.stream().filter(isNotEqualToZero()).collect(Collectors.toList());
    }

    private Predicate<Balance> isNotEqualToZero() {
        return balance -> !balance.getFree().unscaledValue().equals(BigInteger.ZERO) || !balance.getLocked().unscaledValue().equals(BigInteger.ZERO);
    }
}