package pl.wwsi.pr.store.modules.pc;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class Consumer extends Thread {

    private Buffer buf;
    private int number;

    public Consumer(Buffer c, int number) {
        buf = c;
        this.number = number;
    }

    public void run() {
        log.info("Consumer no. {} started", this.number);

        int value = 0;
        Random r = new Random();

        for (int i = 1; i <= r.nextInt(10); i++) {
            value = buf.get();
            log.info("Consumer no. " + this.number + " id: " + this.getId() + " got: " + value);
        }
    }
}
