package pl.wwsi.pr.store.modules.products;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {
    @Id
    private String id;
    private String name;
    private BigDecimal unitPrice;
    private Integer amount;
}
