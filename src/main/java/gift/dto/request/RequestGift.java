package gift.dto.request;

import gift.entity.Gift;

public record RequestGift(Long giftId, String giftName, Integer giftPrice, String giftPhotoUrl) {
    public static Gift toEntity (RequestGift requestGift) {
        return new Gift(
                requestGift.giftId(),
                requestGift.giftName(),
                requestGift.giftPrice(),
                requestGift.giftPhotoUrl()
        );
    }
}
