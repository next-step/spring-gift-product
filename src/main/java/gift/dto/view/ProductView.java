package gift.dto.view;

import gift.dto.response.ProductResponseDto;


public record ProductView(
        Long id,
        String name,
        String formattedPrice,
        String imageUrl
) {
    public static ProductView from(ProductResponseDto responseDto){
        String formattedPrice = String.format("%,d원", responseDto.price());

        return new ProductView(
                responseDto.id(),
                responseDto.name(),
                formattedPrice,
                responseDto.imageUrl()
        );
    }
}
