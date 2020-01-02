package pl.wwsi.pr.store.modules.orders.submit.validator;

import io.vavr.control.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wwsi.pr.store.modules.orders.rest.model.ProductDTO;
import pl.wwsi.pr.store.modules.products.Product;
import pl.wwsi.pr.store.modules.products.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductValidator {
    private final ProductRepository productRepository;

    private final String NOT_EXIST_MESSAGE = "Product not exist: ";
    private final String BAD_AMOUNT_MESSAGE = "Wrong amount for product: ";

    public Option<String> validate(final List<ProductDTO> products) {
        return validateThatAllProductExist(products)
                .orElse(() -> validateThatProductAmountIsEnough(products));
    }

    private Option<String> validateThatAllProductExist(final List<ProductDTO> products) {
        List<ProductDTO> notExistingProducts = products.stream()
                .filter(product ->
                        {
                            Optional<Product> oProduct = productRepository.findByName(product.getName());
                            return !oProduct.isPresent();

                        }
                ).collect(Collectors.toList());
        if (notExistingProducts.stream().findFirst().isPresent()) {
            return Option.of(NOT_EXIST_MESSAGE + notExistingProducts.stream().findFirst().get().getName());
        } else {
            return Option.none();
        }
    }

    private Option<String> validateThatProductAmountIsEnough(final List<ProductDTO> products) {
        List<ProductDTO> badAmountProducts = products.stream()
                .filter(product ->
                        {
                            Optional<Product> oProduct = productRepository.findByName(product.getName());
                            return oProduct.get().getAmount() < product.getAmount();

                        }
                ).collect(Collectors.toList());
        if (badAmountProducts.stream().findFirst().isPresent()) {
            return Option.of(BAD_AMOUNT_MESSAGE + badAmountProducts.stream().findFirst().get().getName());
        } else {
            return Option.none();
        }
    }
}
