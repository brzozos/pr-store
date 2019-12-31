package pl.wwsi.pr.store.modules.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.wwsi.pr.store.modules.orders.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
