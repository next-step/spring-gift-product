package gift.dto.request;

import gift.entity.Gift;

public record RequestModifyGift(
        Long giftId,
        String giftName,
        Integer giftPrice,
        String giftPhotoUrl
){
    public static Gift toEntity(RequestModifyGift requestModifyGift){
        return new Gift(
                requestModifyGift.giftId(),
                requestModifyGift.giftName(),
                requestModifyGift.giftPrice(),
                requestModifyGift.giftPhotoUrl()
        );
    }
}
