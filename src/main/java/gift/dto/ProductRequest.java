package gift.dto;

import gift.exception.BusinessException;
import gift.exception.ErrorCode;

public record ProductRequest(String name, String price, String imageUrl) {

    public Integer validatedPrice() {
        try {
            var bigInt = new java.math.BigInteger(price);
            if (bigInt.compareTo(java.math.BigInteger.ZERO) < 0 ||
                    bigInt.compareTo(java.math.BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
                throw new BusinessException(ErrorCode.PRICE_OUT_OF_RANGE);
            }
            return bigInt.intValue();
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.INVALID_PRICE_FORMAT);
        }
    }
}
