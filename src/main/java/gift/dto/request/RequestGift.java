package gift.dto.request;

import gift.entity.Gift;

public record RequestGift(Long id, String name, Integer price, String imageUrl) {
    public static Gift toEntity (RequestGift requestGift) {
        return new Gift(
                requestGift.id(),
                requestGift.name(),
                requestGift.price(),
                requestGift.imageUrl()
        );
    }
}
