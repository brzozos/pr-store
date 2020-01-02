package pl.wwsi.pr.store.modules.pc;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j
public class Buffer {

    private final int bufferCapacity = 5;

    private LinkedList<Integer> contents = new LinkedList<>();

    public synchronized int get(int consumerNumber) {
        while (contents.size() == 0) {
            try {
                log.info("Buffer is empty for consumer {}", consumerNumber);
                wait();
            } catch (InterruptedException e) {
                log.error("Exception occurred while getting object from Buffer", e);
            }
        }
        notifyAll();

        return contents.removeFirst();
    }

    public synchronized void put(int value, int producerNumber) {
        while (contents.size() == bufferCapacity) {
            try {
                log.info("Buffer is full for producer {}", producerNumber);
                wait();
            } catch (InterruptedException e) {
                log.error("Exception occurred while getting object from Buffer", e);
            }
        }

        contents.addLast(value);
        notifyAll();
    }
}
