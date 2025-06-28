package gift.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public record CreateProductRequestDto(String name, Long price, String imageUrl) {

    public CreateProductRequestDto {
        if (name == null || price == null || imageUrl == null || price < 0L) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
