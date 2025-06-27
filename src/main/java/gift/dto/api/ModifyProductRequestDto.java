package gift.dto.api;

/**
 * @param name     상품의 이름
 * @param price    상품의 가격
 * @param imageUrl 상품의 이미지 URL
 */
public record ModifyProductRequestDto(String name, Long price, String imageUrl) {
    
    public Boolean isNotValidForModify() {
        return (name == null || price == null || imageUrl == null);
    }
    
    public Boolean isNotValidForModifyInfo() {
        return (name == null && price == null && imageUrl == null);
    }
}
