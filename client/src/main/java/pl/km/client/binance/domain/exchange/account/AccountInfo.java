package pl.km.client.binance.domain.exchange.account;

import lombok.Data;

import java.util.List;

@Data
public class AccountInfo {
    private List<Balance> balances;
}
