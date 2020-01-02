package pl.wwsi.pr.store.modules.pc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer extends Thread {

    private Buffer buffer;
    private int number;

    public Consumer(Buffer buffer, int number) {
        this.buffer = buffer;
        this.number = number;
    }

    public void run() {
        log.info("Consumer no. {} started", this.number);

        int value = 0;

        while (true) {
            value = buffer.get(this.number);
            log.info("Consumer no. " + this.number + " id: " + this.getId() + " got: " + value);
        }
    }
}
