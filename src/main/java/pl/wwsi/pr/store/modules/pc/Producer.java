package pl.wwsi.pr.store.modules.pc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.wwsi.pr.store.modules.orders.listener.model.NewOrderEvent;
import pl.wwsi.pr.store.modules.orders.process.NewOrderProcessor;
import pl.wwsi.pr.store.modules.orders.rest.model.OrderDTO;
import pl.wwsi.pr.store.modules.orders.rest.model.ProductDTO;
import pl.wwsi.pr.store.modules.orders.rest.model.SubmittedOrder;
import pl.wwsi.pr.store.modules.orders.submit.OrderSubmitter;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class Producer extends Thread {

    private final Buffer buffer;
    private final int number;

    private final OrderSubmitter orderSubmitter;
    private final NewOrderProcessor newOrderProcessor;

    public void run() {
        log.info("Producer no. {} started", this.number);

        for (int i = 0; i < 500; i++) {
            SubmittedOrder submittedOrder = orderSubmitter.submit(createSimulatedOrder());
            newOrderProcessor.process(new NewOrderEvent(submittedOrder.getId()));

            buffer.put(submittedOrder.getId(), this.number);
            log.info("Producer no. " + this.number + " put order with id: " + submittedOrder.getId());
        }
    }

    private OrderDTO createSimulatedOrder() {
        OrderDTO order = new OrderDTO();
        order.setUserName("Łukasz");
        order.setUserSurname("Bieńkowski");
        order.setUserAddress("Rogalinska 1 01-206 Warszawa");
        order.setPublish(false);
        order.setProducts(createSimulatedProducts());

        return order;
    }

    private List<ProductDTO> createSimulatedProducts() {
        List<ProductDTO> products = new ArrayList<>();
        ProductDTO productSamsung = new ProductDTO();
        productSamsung.setAmount(1);
        productSamsung.setName("Samsung galaxy 11");
        products.add(productSamsung);

        ProductDTO productIphone = new ProductDTO();
        productIphone.setAmount(3);
        productIphone.setName("Aj fon 6");
        products.add(productIphone);
        return products;
    }
}
