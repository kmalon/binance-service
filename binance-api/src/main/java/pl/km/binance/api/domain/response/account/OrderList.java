package pl.km.binance.api.domain.response.account;

import lombok.Data;

import java.util.List;
@Data
public class OrderList {
    private int orderListId;
    private ContingencyType contingencyType;


    private List<Order> orders;
}
