package gift.exception;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;

public class InvalidPriceException extends ApiException{
    public InvalidPriceException() {
        super("Price will be over 0", HttpStatus.BAD_REQUEST);
    }
}
