package pl.wwsi.pr.store.modules.orders.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.wwsi.pr.store.modules.orders.rest.model.OrderDTO;
import pl.wwsi.pr.store.modules.orders.rest.model.SubmittedOrder;
import pl.wwsi.pr.store.modules.orders.submit.OrderSubmitter;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderSubmitter submitter;

    @PostMapping("/order")
    public ResponseEntity<SubmittedOrder> submitOrder(@RequestBody final OrderDTO orderDTO) {
        return new ResponseEntity<>(submitter.submit(orderDTO), HttpStatus.OK);
    }

}
