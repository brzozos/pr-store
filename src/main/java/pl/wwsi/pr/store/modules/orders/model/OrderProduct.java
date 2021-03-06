package pl.wwsi.pr.store.modules.orders.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderProduct {
    private String id;
    private String name;
    private Integer amount;
    private BigDecimal unitPrice;
}
