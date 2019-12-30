package pl.wwsi.pr.store.modules.pc;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class Producer extends Thread {

    private Buffer buf;
    private int number;

    public Producer(Buffer c, int number) {
        buf = c;
        this.number = number;
    }

    public void run() {
        log.info("Producer no. {} started", this.number);

        Random r = new Random();

        for (int i = 1; i <= r.nextInt(10); i++) {
            buf.put(i);
            log.info("Producer no. " + this.number + " id: " + this.getId() + " put: " + i);
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                log.error("Exception occurred while running Producer {}, Thread", i, e);
            }
        }
    }
}
