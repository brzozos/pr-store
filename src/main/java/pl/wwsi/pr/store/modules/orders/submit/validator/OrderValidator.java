package pl.wwsi.pr.store.modules.orders.submit.validator;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.wwsi.pr.store.modules.orders.rest.exception.OrderException;
import pl.wwsi.pr.store.modules.orders.rest.model.OrderDTO;

@Service
@RequiredArgsConstructor
public class OrderValidator {
    private final ProductValidator productValidator;

    public void validate(final OrderDTO orderDTO) {
        val validationResult = productValidator.validate(orderDTO.getProducts());
        if (!validationResult.isEmpty()) {
            throw new OrderException(validationResult.get());
        }
    }
}
