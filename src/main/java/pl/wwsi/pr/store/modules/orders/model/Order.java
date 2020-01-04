package pl.wwsi.pr.store.modules.orders.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    @Id
    private String id;
    private String userName;
    private String userSurname;
    private String userAddress;
    private List<OrderProduct> products;
    private BigDecimal totalValue;
    private OrderRoute route;
    private LocalDateTime created;
    private OrderStatus status;
}
