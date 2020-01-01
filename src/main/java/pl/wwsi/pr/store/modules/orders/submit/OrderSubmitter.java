package pl.wwsi.pr.store.modules.orders.submit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.wwsi.pr.store.modules.orders.model.Order;
import pl.wwsi.pr.store.modules.orders.model.OrderProduct;
import pl.wwsi.pr.store.modules.orders.repository.OrderRepository;
import pl.wwsi.pr.store.modules.orders.rest.model.OrderDTO;
import pl.wwsi.pr.store.modules.orders.rest.model.ProductDTO;
import pl.wwsi.pr.store.modules.orders.rest.model.SubmittedOrder;
import pl.wwsi.pr.store.modules.orders.submit.validator.OrderValidator;
import pl.wwsi.pr.store.modules.products.ProductRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderSubmitter {
    private final OrderValidator orderValidator;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final NewOrderPublisher newOrderPublisher;

    public SubmittedOrder submit(final OrderDTO orderDTO) {
        orderValidator.validate(orderDTO);

        orderDTO.getProducts()
                .forEach(this::reserveProducts);

        Order order = createOrder(orderDTO);

        Order savedOrder = orderRepository.save(order);

        newOrderPublisher.publish(savedOrder.getId());

        return createSubmittedOrder(orderDTO, order);
    }

    private SubmittedOrder createSubmittedOrder(final OrderDTO orderDTO, final Order order) {
        val submittedOrder = new SubmittedOrder();
        submittedOrder.setProducts(orderDTO.getProducts());
        submittedOrder.setTotalValue(order.getTotalValue());
        submittedOrder.setUserAddress(orderDTO.getUserAddress());
        submittedOrder.setUserName(orderDTO.getUserName());
        submittedOrder.setUserSurname(orderDTO.getUserSurname());
        return submittedOrder;
    }

    private Order createOrder(final OrderDTO orderDTO) {
        val order = new Order();
        order.setProducts(orderDTO.getProducts().stream()
                .map(productDTO -> {
                    val orderProduct = new OrderProduct();
                    orderProduct.setId(productDTO.getId());
                    return orderProduct;
                })
                .collect(Collectors.toList()));
        order.setUserAddress(orderDTO.getUserAddress());
        order.setUserName(orderDTO.getUserName());
        order.setUserSurname(orderDTO.getUserSurname());
        order.setTotalValue(orderDTO.getProducts().stream()
                .map(productDTO -> productDTO.getUnitPrice().multiply(new BigDecimal(productDTO.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        order.setCreated(LocalDateTime.now());
        return order;
    }

    private void reserveProducts(final ProductDTO productDTO) {
        val product = productRepository.findByName(productDTO.getName()).get();
        product.setAmount(product.getAmount() - productDTO.getAmount());
        productDTO.setId(product.getId());
        productDTO.setUnitPrice(product.getUnitPrice());
        productRepository.save(product);
    }
}
