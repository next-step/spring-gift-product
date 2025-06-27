package gift.dto;

public record ProductUpdateRequestDto(String name, Long price, String url){
    public ProductUpdateRequestDto(){
        this(null, null, null);
    }
}
