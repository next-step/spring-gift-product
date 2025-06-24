package gift.dto;

import gift.entity.Product;

public class ResponseDto {

    private Long id;
    private String name;

    public ResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
    }
}
