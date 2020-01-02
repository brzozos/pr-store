package pl.wwsi.pr.store.modules.pc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.wwsi.pr.store.modules.orders.process.NewOrderProcessor;
import pl.wwsi.pr.store.modules.orders.repository.OrderRepository;
import pl.wwsi.pr.store.modules.orders.submit.OrderSubmitter;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProducerConsumer implements CommandLineRunner {

    private final OrderSubmitter orderSubmitter;
    private final NewOrderProcessor newOrderProcessor;
    private final OrderRepository orderRepository;

    private final int producerNumber = 6;
    private final int consumerNumber = 2;

    @Override
    public void run(String... args) {
        log.info("Starting PC algorithm");

        Buffer buffer = new Buffer();

        List<Producer> producers = createProducers(buffer);
        List<Consumer> consumers = createConsumers(buffer);

        producers.forEach(Producer::start);
        consumers.forEach(Consumer::start);
    }

    private List<Producer> createProducers(Buffer buffer) {
        List<Producer> producers = new ArrayList<>();

        for (int i = 1; i <= producerNumber; i++) {
            Producer producer = new Producer(buffer, i, orderSubmitter, newOrderProcessor);
            producers.add(producer);
        }

        return producers;
    }

    private List<Consumer> createConsumers(Buffer buffer) {
        List<Consumer> consumers = new ArrayList<>();

        for (int i = 1; i <= consumerNumber; i++) {
            Consumer consumer = new Consumer(buffer, i, orderRepository);
            consumers.add(consumer);
        }

        return consumers;
    }
}
