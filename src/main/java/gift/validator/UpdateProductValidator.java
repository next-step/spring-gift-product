package gift.validator;

import gift.dto.request.UpdateProductDto;
import gift.exception.RequestValidateFailException;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductValidator {

    public void validate(UpdateProductDto dto) {
        if (dto.name() == null || dto.name().isBlank()) {
            throw new RequestValidateFailException("Product name is Required!");
        }
        if (dto.price() == null) {
            throw new RequestValidateFailException("Product price is Required!");
        }
        if (dto.price() < 0) {
            throw new RequestValidateFailException("Price must be zero or greater");
        }
    }
}
