package gift.dto.request;

import gift.entity.Gift;

public record RequestGift(Long id, String name, Integer price, String imageUrl) {
    public static Gift from (RequestGift requestGift) {
        return new Gift(
                requestGift.name(),
                requestGift.price(),
                requestGift.imageUrl()
        );
    }
}
