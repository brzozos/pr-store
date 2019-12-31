package pl.wwsi.pr.store.modules.orders.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class Order {
    private String userName;
    private String userSurname;
    private String userAddress;
    private List<OrderProduct> products;
    private BigDecimal totalValue;
}
