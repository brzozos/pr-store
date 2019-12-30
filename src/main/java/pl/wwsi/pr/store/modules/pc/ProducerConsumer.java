package pl.wwsi.pr.store.modules.pc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ProducerConsumer implements CommandLineRunner {

    private final int producerNumber = 1;
    private final int consumerNumber = 10;

    @Override
    public void run(String... args) {
        log.info("Starting PC algorithm");

        Buffer c = new Buffer();

        List<Producer> producers = createProducers(c);
        List<Consumer> consumers = createConsumers(c);

        producers.forEach(Producer::start);
        consumers.forEach(Consumer::start);
    }

    private List<Producer> createProducers(Buffer c) {
        List<Producer> producers = new ArrayList<>();

        for (int i = 1; i <= producerNumber; i++) {
            Producer producer = new Producer(c, i);
            producers.add(producer);
        }

        return producers;
    }

    private List<Consumer> createConsumers(Buffer c) {
        List<Consumer> consumers = new ArrayList<>();

        for (int i = 1; i <= consumerNumber; i++) {
            Consumer consumer = new Consumer(c, i);
            consumers.add(consumer);
        }

        return consumers;
    }
}
