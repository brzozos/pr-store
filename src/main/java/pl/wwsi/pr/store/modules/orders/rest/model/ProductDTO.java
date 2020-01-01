package pl.wwsi.pr.store.modules.orders.rest.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductDTO {
    private String id;
    private String name;
    private Integer amount;
    private BigDecimal unitPrice;
}
