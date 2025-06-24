package gift.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
  NOT_EXISTS_PRODUCT(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),
  INVALID_PRODUCT_REQUEST(HttpStatus.BAD_REQUEST, "상품 이름, 가격, 이미지 주소는 필수 정보입니다."),
  INVALID_PRODUCT_PRICE(HttpStatus.BAD_REQUEST, "상품 가격은 0원 이상이어야 합니다.");

  private final HttpStatus httpStatus;
  private final String errorMessage;

  ErrorCode(HttpStatus httpStatus, String errorMessage){
    this.httpStatus=httpStatus;
    this.errorMessage=errorMessage;
  }

  public HttpStatus getHttpStatus(){
    return this.httpStatus;
  }

  public String getErrorMessage(){
    return this.errorMessage;
  }
}
