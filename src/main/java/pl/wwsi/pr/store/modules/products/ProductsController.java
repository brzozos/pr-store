package pl.wwsi.pr.store.modules.products;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductsController {
    private final ProductRepository repository;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllAvailableProducts() {
        return  new ResponseEntity<>(repository.findByAmountGreaterThan(0), HttpStatus.OK);
    }


    @PostMapping("/product")
    public ResponseEntity<List<Product>> init() {
        Random random = new Random();

        val products = Stream.of(
                createProduct(random, "Aj fon 6", new BigDecimal(9999)),
                createProduct(random, "Samsung galaxy 9", new BigDecimal(6000)),
                createProduct(random, "Samsung galaxy 10", new BigDecimal(7000)),
                createProduct(random, "Samsung galaxy 11", new BigDecimal(8000)),
                createProduct(random, "Ciajomi Mi A1", new BigDecimal(700)),
                createProduct(random, "Ciajomi Mi A2", new BigDecimal(800)),
                createProduct(random, "Etui aj fon 6", new BigDecimal(20)),
                createProduct(random, "Charger type c", new BigDecimal(11.99)),
                createProduct(random, "Charger wireless", new BigDecimal(19.99)),
                createProduct(random, "Headphones type c", new BigDecimal(99.99)),
                createProduct(random, "Bluetooth headphones", new BigDecimal(179.99)),
                createProduct(random, "Screen cleaner", new BigDecimal(5.49))
        ).collect(Collectors.toList());

        val createdProducts = repository.saveAll(products);


        return  new ResponseEntity<>(createdProducts, HttpStatus.OK);
    }

    private Product createProduct(final Random random, final String name, final BigDecimal unitPrice) {
        Product product = new Product();
        product.setAmount(random.nextInt(99));
        product.setName(name);
        product.setUnitPrice(unitPrice);
        return product;
    }
}
