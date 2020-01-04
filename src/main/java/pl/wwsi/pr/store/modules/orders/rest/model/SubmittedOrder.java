package pl.wwsi.pr.store.modules.orders.rest.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SubmittedOrder {
    private String id;
    private String userName;
    private String userSurname;
    private String userAddress;
    private List<ProductDTO> products;
    private BigDecimal totalValue;
}
