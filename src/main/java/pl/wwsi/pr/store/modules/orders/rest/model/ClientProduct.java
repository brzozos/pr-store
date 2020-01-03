package pl.wwsi.pr.store.modules.orders.rest.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ClientProduct {
    private String name;
    private Integer amount;
    private BigDecimal unitPrice;
}
