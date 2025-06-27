package gift.dto.api;

/**
 * @param name     상품의 이름
 * @param price    상품의 가격
 * @param imageUrl 상품의 이미지 URL
 */
public record AddProductRequestDto(String name, Long price, String imageUrl) {
    
    //유효성 검사 묶기
    public Boolean isNotValid() {
        return (name == null || price == null || imageUrl == null);
    }
}
