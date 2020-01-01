package pl.wwsi.pr.store.modules.orders.rest.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class SubmittedOrder {
    private String userName;
    private String userSurname;
    private String userAddress;
    private List<ProductDTO> products;
    private BigDecimal totalValue;
}
