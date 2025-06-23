# spring-gift-product

## 상품 관리
### 🚀 1단계 - 상품 API
상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

#### 🛠 구현할 기능 목록
- [ ] 상품 추가 API
  - [ ] 단건 상품 추가
    - **Request**: POST /api/products
      ```json
      {
          "name": "아이스 카페 아메리카노 T",
          "price": 4500,
          "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
      }
      ```
    - **Response**: 201 Created
      ```json 
      {
          "id": 8146027,
          "name": "아이스 카페 아메리카노 T",
          "price": 4500,
          "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
      }
      ```
- [ ] 상품 조회 API
  - [ ] 전체 상품 조회
    - **Request**: GET /api/products
    - **Response**: 200 OK
      ```json
      [
        {
        "id": 8146027,
        "name": "아이스 카페 아메리카노 T",
        "price": 4500,
        "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
        }
      ]
      ```
  - [ ] 단건 상품 조회
    - **Request**: GET /api/products/{id}
    - **Response**: 200 OK
      ```json
      {
          "id": 8146027,
          "name": "아이스 카페 아메리카노 T",
          "price": 4500,
          "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
      }
      ```
    - [ ] **예외**:
      - id가 존재하지 않을 때: 404 Not Found
- [ ] 상품 수정 API
  - [ ] 단건 상품 전체 수정
    - **Request**: PUT /api/products/{id}
      ```json
      {
          "name": "아이스 카페 아메리카노 T",
          "price": 9000,
          "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
      }
      ```
    - **Response**: 200 OK
      ```json
      {
          "id": 8146027,
          "name": "아이스 카페 아메리카노 T",
          "price": 9000,
          "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
      }
      ```
    - [ ] **예외**:
      - id가 존재하지 않을 때: 404 Not Found
      - name, price, imageUrl 중 하나라도 존재하지 않을 때: 400 Bad Request
- [ ] 상품 삭제 API