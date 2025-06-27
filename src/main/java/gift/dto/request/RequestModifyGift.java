package gift.dto.request;

public record RequestModifyGift(
        Long giftId,
        String name,
        Integer price,
        String imageUrl
){
}
