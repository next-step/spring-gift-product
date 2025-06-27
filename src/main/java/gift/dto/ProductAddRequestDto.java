package gift.dto;

public record ProductAddRequestDto(String name, Long price, String url) {
    public ProductAddRequestDto(){
        this(null, null, null);
    }
}
