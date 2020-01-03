package pl.wwsi.pr.store.modules.orders.repository;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wwsi.pr.store.modules.orders.model.Order;
import pl.wwsi.pr.store.modules.orders.rest.exception.OrderException;
import pl.wwsi.pr.store.modules.orders.rest.model.ClientOrder;
import pl.wwsi.pr.store.modules.orders.rest.model.ClientProduct;

@Service
@RequiredArgsConstructor
public class OrderFinder {
    private final OrderRepository orderRepository;

    public ClientOrder findOrderById(String id) {
        return orderRepository.findById(id)
                .map(this::convertToClientOrder)
                .orElseThrow(() -> new OrderException("Order not found, id: " + id));
    }

    private ClientOrder convertToClientOrder(final Order order) {
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setId(order.getId());
        clientOrder.setCreated(order.getCreated());
        clientOrder.setProducts(getClientProducts(order));
        clientOrder.setTotalValue(order.getTotalValue());
        clientOrder.setUserAddress(order.getUserAddress());
        clientOrder.setUserName(order.getUserName());
        clientOrder.setUserSurname(order.getUserSurname());
        return clientOrder;
    }

    private List<ClientProduct> getClientProducts(final Order order) {
        return order.getProducts().stream()
                .map(p -> {
                    ClientProduct clientProduct = new ClientProduct();
                    clientProduct.setAmount(p.getAmount());
                    clientProduct.setName(p.getName());
                    clientProduct.setUnitPrice(p.getUnitPrice());
                    return clientProduct;
                })
                .collect(Collectors.toList());
    }
}
