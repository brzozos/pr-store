package pl.wwsi.pr.store.modules.products;


import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByName(String name);
    List<Product> findByAmountGreaterThan(Integer amount);
}
