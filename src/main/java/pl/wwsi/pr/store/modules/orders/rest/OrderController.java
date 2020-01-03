package pl.wwsi.pr.store.modules.orders.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.wwsi.pr.store.modules.orders.repository.OrderFinder;
import pl.wwsi.pr.store.modules.orders.rest.model.ClientOrder;
import pl.wwsi.pr.store.modules.orders.rest.model.OrderDTO;
import pl.wwsi.pr.store.modules.orders.rest.model.SubmittedOrder;
import pl.wwsi.pr.store.modules.orders.submit.OrderSubmitter;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderSubmitter submitter;
    private final OrderFinder orderFinder;

    @PostMapping("/order")
    public ResponseEntity<SubmittedOrder> submitOrder(@RequestBody final OrderDTO orderDTO) {
        orderDTO.setPublish(true);
        return new ResponseEntity<>(submitter.submit(orderDTO), HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<ClientOrder> submitOrder(@PathVariable final String id) {
        return new ResponseEntity<>(orderFinder.findOrderById(id), HttpStatus.OK);
    }

}
