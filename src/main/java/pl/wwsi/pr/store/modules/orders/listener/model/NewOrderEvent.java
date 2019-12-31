package pl.wwsi.pr.store.modules.orders.listener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderEvent {
    private String orderId;
}
