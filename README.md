# spring-gift-product

## 기능 목록

### Step 1

- [x] 상품 등록 (POST /api/products)
- [x] 상품 목록 조회 (GET /api/products)
- [x] 상품 개별 조회 (GET /api/products/{id})
- [x] 상품 수정 (PUT /api/products/{id})
- [x] 상품 삭제 (DELETE /api/products/{id})

1. 상품 등록 (POST /api/products)
  - 상품 정보를 등록합니다.  
  - 요청 본문은 JSON 형식이며, 모든 필드(name, price, imageUrl)는 필수입니다.
  - **URL**: `/api/products`
  - **Method**: `POST`
  - **Content-Type**: `application/json`
  - 요청 예시
    ```json
    {
      "name": "초콜릿",
      "price": 1000,
      "imageUrl": "https://i.namu.wiki/i/p5SG0f_Sux-XgAa3uNUsJLG_VeWFakukCtXW4kAiWZoiTqjYq0xVDJGpWqga8_LNZGQoCLf-E-gCev-GLYdibGQgQFny_R44zfBbtwU52rpJ1d_sOkzZb8CaBO7DTi3mTrTkRW_SN7wBX9DlAa0zBg.webp"
    }
  - 응답 예시
    ```json
    {
      "id": 1,
      "name": "초콜릿",
      "price": 1000,
      "imageUrl": "https://i.namu.wiki/i/p5SG0f_Sux-XgAa3uNUsJLG_VeWFakukCtXW4kAiWZoiTqjYq0xVDJGpWqga8_LNZGQoCLf-E-gCev-GLYdibGQgQFny_R44zfBbtwU52rpJ1d_sOkzZb8CaBO7DTi3mTrTkRW_SN7wBX9DlAa0zBg.webp"
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
        "price": 1000,
        "imageUrl": "https://i.namu.wiki/i/p5SG0f_Sux-XgAa3uNUsJLG_VeWFakukCtXW4kAiWZoiTqjYq0xVDJGpWqga8_LNZGQoCLf-E-gCev-GLYdibGQgQFny_R44zfBbtwU52rpJ1d_sOkzZb8CaBO7DTi3mTrTkRW_SN7wBX9DlAa0zBg.webp"
      },
      {
        "id": 2,
        "name": "커피",
        "price": 4500,
        "imageUrl": "https://i.namu.wiki/i/OPfz4S6ZJjWE5_e3Drmrzrgufe3hP-5wyR71QWp3LD3MXoKU2cd3Stnry_yQZgD4MjmwbRfTnOGE87Y9ZS8qkZWGnoeeOBeqpS9Bv3v7P5vVsaMU97ukaJ5PXZF-mmkFY_rtqNoQR5KsQWbbU3Mq0g.webp"
      }
    ]

3. 상품 개별 조회 (GET /api/products/{id})
  - 특정 상품 ID를 이용해 단일 상품 정보를 조회합니다.
  - **URL**: `/api/products/{id}`
  - **Method**: `GET`
  - **Path Variable**: `id` (상품 ID, Long)
  - **Response Content-Type**: `application/json`
  - 응답 예시 (200 OK)
    ```json
    {
      "id": 1,
      "name": "초콜릿",
      "price": 1000,
      "imageUrl": "https://i.namu.wiki/i/p5SG0f_Sux-XgAa3uNUsJLG_VeWFakukCtXW4kAiWZoiTqjYq0xVDJGpWqga8_LNZGQoCLf-E-gCev-GLYdibGQgQFny_R44zfBbtwU52rpJ1d_sOkzZb8CaBO7DTi3mTrTkRW_SN7wBX9DlAa0zBg.webp"
    }
  - 예외 처리
    - 존재하지 않는 ID로 요청이 올 경우에는 "상품을 찾을 수 없습니다."라는 메세지를 응답하고 404 Not Found 상태 코드 반환
  - 내부 검증 방식
    - ProductValidator.validateExists(id, repository) 를 통해 상품 존재 여부를 검증
    - 검증에 실패할 경우 NoSuchElementException 을 발생시키고, GlobalExceptionHandler 에서 이를 잡아 404 상태 코드로 응답

3. 상품 수정 (PUT /api/products/{id})
  - 특정 상품 정보를 수정합니다.
  - 요청 본문에는 수정할 name, price, imageUrl이 포함되어야 하며, 기존 상품을 덮어씁니다.
  - **URL**: `/api/products/{id}`
  - **Method**: `PUT`
  - **Content-Type**: `application/json`
  - **Path Variable**: `id` (상품 ID)
  - 요청 예시
    ```json
    {
      "name": "새우깡",
      "price": 1000,
      "imageUrl": "https://i.namu.wiki/i/v12co4dPL1DydzL3X4YFYFVNLLYe07BO2zSofx2LmrUG7g1PNWaBOC5Gwa2ip1r6EUxYIYzRze-9qRZVTQeq0lVjv91X_cWPVPb0HXitAg7NoCHDqpvbPZUHE3WyyAE2uead00JqL5w6uIxQQYTDPA.webp"
    }
  - 응답 예시 (200 OK)
    ```json
    {
      "id": 1,
      "name": "새우깡",
      "price": 1000,
      "imageUrl": "https://i.namu.wiki/i/v12co4dPL1DydzL3X4YFYFVNLLYe07BO2zSofx2LmrUG7g1PNWaBOC5Gwa2ip1r6EUxYIYzRze-9qRZVTQeq0lVjv91X_cWPVPb0HXitAg7NoCHDqpvbPZUHE3WyyAE2uead00JqL5w6uIxQQYTDPA.webp"
    }
  - 예외 처리
    - 가격이 0 미만이면 "가격은 0 이상이어야 합니다." 메세지를 응답하고 400 Bad Request 상태 코드 반환
    - 잘못된 이미지 URL 요청이 들어오면 "유효한 이미지 URL이 아닙니다." 메세지를 응답하고 400 Bad Request 상태 코드 반환
    - 필수 필드 누락 (name, price, imageUrl) 시에는 "상품명, 가격, 이미지 URL은 필수입니다." 메세지를 응답하고 400 Bad Request 상태 코드 반환
    - 존재하지 않는 ID로 요청이 올 경우에는 "상품을 찾을 수 없습니다."라는 메세지를 응답하고 404 Not Found 상태 코드 반환
  - 내부 검증 방식
    - 수정 시에도 등록과 동일한 유효성 검사를 수행 (ProductValidator)
    - 수정 후에도 ID는 변하지 않으며, 기존 상품 객체의 필드만 변경

5. 상품 삭제 (DELETE /api/products/{id})
  - 특정 상품을 삭제합니다.
  - 존재하지 않는 상품 ID를 요청할 경우 404 Not Found 응답을 반환합니다.
  - **URL**: `/api/products/{id}`
  - **Method**: `DELETE`
  - **Path Variable**: `id` (상품 ID)
  - 응답 예시 (204 No Content)
    - 본문 없이 상태 코드만 반환됩니다.
  - 예외 처리
    - 존재하지 않는 ID로 요청이 올 경우에는 "상품을 찾을 수 없습니다."라는 메세지를 응답하고 404 Not Found 상태 코드 반환
  - 내부 검증 방식
    - 삭제 전에 해당 상품의 존재 여부를 ProductValidator 에서 검증
      - 검증 실패 시 NoSuchElementException 발생 → GlobalExceptionHandler 가 404로 처리