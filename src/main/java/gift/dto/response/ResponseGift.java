package gift.dto.response;

import gift.entity.Gift;

public record ResponseGift(
        Long id,
        Long giftId,
        String giftName,
        Integer giftPrice,
        String giftPhotoUrl
) {
    public static ResponseGift from(Gift gift) {
        return new ResponseGift(
                gift.getId(),
                gift.getGiftId(),
                gift.getGiftName(),
                gift.getGiftPrice(),
                gift.getGiftPhotoUrl()
        );
    }
}
