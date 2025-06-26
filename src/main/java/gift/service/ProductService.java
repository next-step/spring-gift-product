package gift.service;

import gift.dto.RequestDto;
import gift.dto.ResponseDto;

public interface ProductService {

    ResponseDto saveProduct(RequestDto requestDto);
}
