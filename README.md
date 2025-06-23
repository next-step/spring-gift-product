# spring-gift-product

## 기능 목록

### Step 1

- [ ] 상품 등록 (POST /api/products)
- [ ] 상품 목록 조회 (GET /api/products)
- [ ] 상품 수정 (PUT /api/products/{id})
- [ ] 상품 삭제 (DELETE /api/products/{id})

1. 상품 등록 (POST /api/products)
  - 상품 정보를 등록합니다.  
  - 요청 본문은 JSON 형식이며, 모든 필드(name, price, imageUrl)는 필수입니다.
  - **URL**: `/api/products`
  - **Method**: `POST`
  - **Content-Type**: `application/json`
  - 요청 예시
    - {
        "name": "초콜릿",
        "price": 3000,
        "imageUrl": "https://cdn.pixabay.com/photo/2017/02/03/14/47/chocolate-2038186_1280.jpg"
      }
  - 응답 예시
    - {
        "id": 1,
        "name": "초콜릿",
        "price": 3000,
        "imageUrl": "https://cdn.pixabay.com/photo/2017/02/03/14/47/chocolate-2038186_1280.jpg"
      }
  - 예외 처리
    - 가격이 0 미만이면 "가격은 0 이상이어야 합니다." 메세지를 응답하고 400 Bad Request 상태 코드 반환
    - 잘못된 이미지 URL 요청이 들어오면 "유효한 이미지 URL이 아닙니다." 메세지를 응답하고 400 Bad Request 상태 코드 반환
    - 필수 필드 누락 (name, price, imageUrl) 시에는 "상품명, 가격, 이미지 URL은 필수입니다." 메세지를 응답하고 400 Bad Request 상태 코드 반환

2. 상품 목록 조회 (GET /api/products)
  - 등록된 모든 상품을 조회합니다.  
  - 응답은 상품 객체의 JSON 배열로 반환됩니다.
  - **URL**: `/api/products`
  - **Method**: `GET`
  - **Response Content-Type**: `application/json`
  - 응답 예시 (200 ok)
    ```json
    [
      {
        "id": 1,
        "name": "초콜릿",
        "price": 3000,
        "imageUrl": "https://cdn.pixabay.com/photo/2017/02/03/14/47/chocolate-2038186_1280.jpg"
      },
      {
        "id": 2,
        "name": "커피",
        "price": 4500,
        "imageUrl": "https://cdn.pixabay.com/photo/2015/04/08/13/13/coffee-712661_1280.jpg"
      }
    ]