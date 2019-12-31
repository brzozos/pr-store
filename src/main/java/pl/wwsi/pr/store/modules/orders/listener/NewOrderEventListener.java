package pl.wwsi.pr.store.modules.orders.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.wwsi.pr.store.modules.orders.listener.model.NewOrderEvent;
import pl.wwsi.pr.store.modules.orders.process.NewOrderProcessor;

@Service
@Log4j2
@RequiredArgsConstructor
public class NewOrderEventListener {
    private final String QUEUE_NAME = "wwsi.store.new_order";
    private final NewOrderProcessor processor;

    @RabbitListener(queues = {QUEUE_NAME})
    public void process(final NewOrderEvent event) {
        log.info("Processing event: {}", event);
        processor.process(event);
        log.info("Event processed  {}", event);
    }
}
