package pl.wwsi.pr.store.modules.pc;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class Producer extends Thread {

    private Buffer buffer;
    private int number;

    public Producer(Buffer buffer, int number) {
        this.buffer = buffer;
        this.number = number;
    }

    public void run() {
        log.info("Producer no. {} started", this.number);

        Random r = new Random();

        for (int i = 1; i <= 10; i++) {
            int putValue = r.nextInt();
            buffer.put(putValue, this.number);
            log.info("Producer no. " + this.number + " id: " + this.getId() + " put: " + putValue);
        }
    }
}
