package pl.wwsi.pr.store.modules.orders.submit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import pl.wwsi.pr.store.modules.orders.listener.model.NewOrderEvent;

@Component
@RequiredArgsConstructor
public class NewOrderPublisher {
    private final String ROUTING_KEY = "wwsi.store.new_order";
    private final RabbitTemplate rabbitTemplate;

    public void publish(final String orderId) {
        rabbitTemplate.convertAndSend(ROUTING_KEY, new NewOrderEvent(orderId));
    }

}
