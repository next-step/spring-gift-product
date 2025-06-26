package service;

import gift.dto.RequestDto;
import gift.dto.ResponseDto;

public interface ProductService {

    // 1. 생성
    public ResponseDto create(RequestDto dto);

    // 2. 조회
    public ResponseDto findById(Long id);

    // 3. 수정
    public ResponseDto update(Long id, RequestDto dto);

    // 4. 삭제
    public void delete(Long id);

}
