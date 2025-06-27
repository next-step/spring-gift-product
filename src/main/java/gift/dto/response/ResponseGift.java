package gift.dto.response;

import gift.entity.Gift;

public record ResponseGift(
        Long id,
        Long giftId,
        String giftName,
        Integer giftPrice,
        String giftPhotoUrl
) {
    public static ResponseGift toJSON(Long id, Gift gift) {
        return new ResponseGift(
                id,
                gift.getId(),
                gift.getName(),
                gift.getPrice(),
                gift.getImageUrl()
        );
    }
}
