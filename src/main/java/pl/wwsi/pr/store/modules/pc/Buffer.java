package pl.wwsi.pr.store.modules.pc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Buffer {

    private int contents;
    private boolean available = false;

    public synchronized int get() {
        while (!available) {
            try {
                log.info("Waiting for availaible buffer");
                wait();
            } catch (InterruptedException e) {
                log.error("Exception occurred while getting object from Buffer", e);
            }
        }
        available = false;
        notifyAll();
        return contents;
    }

    public synchronized void put(int value) {
        while (available) {
            try {
                log.info("Waiting to put value: {} to buffer", value);
                wait();
            } catch (InterruptedException e) {
                log.error("Exception occurred while getting object from Buffer", e);
            }
        }

        contents = value;
        available = true;
        notifyAll();
    }
}
