package pl.wwsi.pr.store.modules.pc;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j
public class Buffer {

    private final int bufferCapacity = 5;

    private LinkedList<String> contents = new LinkedList<>();

    public synchronized String get(int consumerNumber) {
        while (contents.size() == 0) {
            try {
                log.info("Buffer is empty, consumer {} is waiting ", consumerNumber);
                wait();
                log.info("Consumer {} is running", consumerNumber);
            } catch (InterruptedException e) {
                log.error("Exception occurred while getting object from Buffer", e);
            }
        }
        notifyAll();

        return contents.removeFirst();
    }

    public synchronized void put(String value, int producerNumber) {
        while (contents.size() == bufferCapacity) {
            try {
                log.info("Buffer is full, producer {} is waiting", producerNumber);
                wait();
                log.info("Producer {} is running", producerNumber);
            } catch (InterruptedException e) {
                log.error("Exception occurred while getting object from Buffer", e);
            }
        }

        contents.addLast(value);
        notifyAll();
    }
}
