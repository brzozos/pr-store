package pl.wwsi.pr.store.modules.orders.listener.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewOrderEvent {
    private String orderId;
}
