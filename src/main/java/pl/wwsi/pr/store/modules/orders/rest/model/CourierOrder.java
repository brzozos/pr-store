package pl.wwsi.pr.store.modules.orders.rest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import pl.wwsi.pr.store.modules.orders.model.OrderRoute;
import pl.wwsi.pr.store.modules.orders.model.OrderStatus;

@Data
public class CourierOrder {
    private String id;
    private String userName;
    private String userSurname;
    private String userAddress;
    private BigDecimal totalValue;
    private OrderRoute route;
    private LocalDateTime created;
    private OrderStatus status;
}
