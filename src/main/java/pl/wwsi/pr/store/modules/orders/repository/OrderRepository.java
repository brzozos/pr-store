package pl.wwsi.pr.store.modules.orders.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.wwsi.pr.store.modules.orders.model.Order;
import pl.wwsi.pr.store.modules.orders.model.OrderStatus;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findAllByStatus(OrderStatus status);
}
