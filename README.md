# spring-gift-product

## 상품 관리

### 🚀 1단계 - 상품 API

상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

#### 🛠 구현할 기능 목록

- [x] 상품 추가 API
    - [x] 단건 상품 추가
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
              "productId": 8146027,
              "name": "아이스 카페 아메리카노 T",
              "price": 4500,
              "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
          }
          ```
        - [x] **예외**:
            - name, price, imageUrl 중 하나라도 값이 존재하지 않을 때: ~~default 값 처리~~ 또는 400 Bad Request

- [x] 상품 조회 API
    - [x] 전체 상품 조회
        - **Request**: GET /api/products
        - **Response**: 200 OK
          ```json
          [
            {
            "productId": 8146027,
            "name": "아이스 카페 아메리카노 T",
            "price": 4500,
            "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
            }
          ]
          ```
    - [x] 단건 상품 조회
        - **Request**: GET /api/products/{productId}
        - **Response**: 200 OK
          ```json
          {
              "productId": 8146027,
              "name": "아이스 카페 아메리카노 T",
              "price": 4500,
              "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
          }
          ```
        - [x] **예외**:
            - 데이터베이스에 productId가 존재하지 않을 때: 404 Not Found

- [x] 상품 수정 API
    - [x] 단건 상품 전체 수정
        - **Request**: PUT /api/products/{productId}
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
              "productId": 8146027,
              "name": "아이스 카페 아메리카노 T",
              "price": 9000,
              "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
          }
          ```
        - [x] **예외**:
            - 데이터베이스에 productId가 존재하지 않을 때: 404 Not Found
            - name, price, imageUrl 중 하나라도 존재하지 않을 때: ~~일부만 수정하도록 처리~~ 또는 400 Bad Request

- [x] 상품 삭제 API
    - [x] 단건 상품 삭제
        - **Request**: DELETE /api/products/{productId}
        - **Response**: 200 OK
          ```json
          {
              "productId": 8146027,
              "name": "아이스 카페 아메리카노 T",
              "price": 4500,
              "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
          }
          ```
        - [x] **예외**:
            - 데이터베이스에 productId가 존재하지 않을 때: 404 Not Found

### 🚀 2단계 - 관리자 화면

상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

- Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다.
- 상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.
- 기본적으로는 HTML 폼 전송 등을 이용한 페이지 이동을 기반으로 하지만, 자바스크립트를 이용한 비동기 작업에 관심이 있다면 이미 만든 상품 API를 이용하여 AJAX 등의
  방식을 적용할 수 있다.

#### 🛠 구현할 기능 목록

- [x] 기능별 화면 구성
    - [x] 상품 목록 페이지
        - /admin/products
        - [x] ➡️ 상품 등록 페이지로 이동
        - 테이블
            - ID, 상품명, 가격, 이미지 URL, Action
            - Action
                - [x] ➡️ 상품 수정 페이지로 이동
                - [x] 상품 삭제

    - [x] 상품 등록(추가) 페이지
        - /admin/products/create-product
            - 입력 폼 구성(이름, 가격, 이미지 URL)
            - [x] ➡️ 상품 목록 페이지로 이동

    - [x] 상품 수정 페이지
        - /admin/products/update-product
            - 수정 폼 구성(이름, 가격, 이미지 URL)

### 🚀 3단계 - 데이터베이스 적용

**[ 기능 요구 사항 ]**

자바 컬렉션 프레임워크를 사용하여 메모리에 저장하던 상품 정보를 데이터베이스에 저장한다.

**[ 프로그래밍 요구 사항 ]**

- 메모리에 저장하고 있던 모든 코드를 제거하고 H2 데이터베이스를 사용하도록 변경한다.
- 사용하는 테이블은 애플리케이션이 실행될 때 구축되어야 한다.

#### 🛠 구현할 기능 목록

- [x] 사용하는 테이블은 애플리케이션이 실행될 때 구축
- [x] 메모리에 저장하고 있던 모든 코드를 제거하고 H2 데이터베이스를 사용하도록 변경
    - [x] 상품 추가 API
        - [x] 단건 상품 추가

    - [x] 상품 조회 API
        - [x] 전체 상품 조회
        - [x] 단건 상품 조회

    - [x] 상품 수정 API
        - [x] 단건 상품 전체 수정

    - [x] 상품 삭제 API
        - [x] 단건 상품 삭제