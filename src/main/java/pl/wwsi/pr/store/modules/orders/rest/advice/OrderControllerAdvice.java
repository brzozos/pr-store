package pl.wwsi.pr.store.modules.orders.rest.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.wwsi.pr.store.modules.orders.rest.exception.OrderException;

@RestControllerAdvice
public class OrderControllerAdvice {

    @ExceptionHandler({ OrderException.class})
    public ResponseEntity<String> handle(final OrderException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
