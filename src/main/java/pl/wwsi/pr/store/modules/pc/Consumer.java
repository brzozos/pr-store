package pl.wwsi.pr.store.modules.pc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.wwsi.pr.store.modules.orders.model.Order;
import pl.wwsi.pr.store.modules.orders.model.OrderStatus;
import pl.wwsi.pr.store.modules.orders.repository.OrderRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class Consumer extends Thread {

    private final Buffer buffer;
    private final int number;
    private final OrderRepository orderRepository;

    public void run() {
        log.info("Consumer no. {} started", this.number);

        while (true) {
            String orderId = buffer.get(this.number);
            Order order = getOrder(orderId);
            log.info("Consumer no. " + this.number + " got order with id: " + orderId);

            order.setStatus(OrderStatus.FINISHED);
        }
    }

    private Order getOrder(String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new RuntimeException("Order not found, id: " + orderId);
        }
    }
}
