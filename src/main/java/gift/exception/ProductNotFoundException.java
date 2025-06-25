package gift.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ApiException{
    public ProductNotFoundException() {
        super("Product not found", HttpStatus.NOT_FOUND);
    }
}
