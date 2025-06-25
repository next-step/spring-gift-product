package gift.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductNotFoundException extends RuntimeException {

    private final Long id;

    public ProductNotFoundException(Long id) {
        super("Product not found with id: " + id);
        this.id = id;
    }
}
