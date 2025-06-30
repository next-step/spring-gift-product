# spring-gift-product

## 미션1 상품 관리
___
## STEP1 상품 API

### 기능 요구 사항
- 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현
- HTTP 요청과 응답은 JSON 형식
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장

### 구현 기능 목록
1. 상품 추가
    - 새 상품 추가
    - 성공적으로 추가되면 201 Created
    - POST /api/products

2. 전체 상품 조회
    - 전체 상품의 정보 반환(조회)
    - GET /api/products

3. 선택 상품 조회
    - 특정 ID에 해당하는 상품의 정보 반환(조회)
    - 성공적으로 조회되면 200 OK
    - 없는 ID면 404 Not Found
    - GET /api/products/{id}

4. 상품 수정
    - 상품 ID에 해당하는 상품의 정보 수정
    - 성공적으로 수정되면 200 OK
    - 없는 ID면 404 Not Found
    - PUT /api/products/{id}

5. 상품 삭제
    - 상품 ID에 해당하는 상품 삭제
    - 성공적으로 삭제되면 204 No Content
    - 없는 ID면 404 Not Found
    - DELETE /api/products/{id}

___
## STEP2 관리자 페이지 구현
1. 상품 목록 페이지
   - /products/view
   - 파라미터를 통해 페이지 크기 설정 가능
   - ex) /products/view?page=1&pageSize=5
     - 기본값: page=1, pageSize=5
2. 상품 등록 페이지
   - /product/add
3. 상품 상세 페이지
   - /product/{id}
4. 상품 수정 페이지
   - /product/edit/{id}
5. 상품 삭제는 별다른 페이지 없이
   - /delete/{id}

___
## STEP3 관리자 페이지 구현
STEP1에서 만들었던  API는 데이터를 메모리에 저장
이를 JDBC template을 사용하여 DB에 저장하도록 수정

1. 상품 조회
2. 상품 추가
3. 상품 수정
4. 상품 삭제

이후 STEP2에서 만든 관리자 페이지에서 정상적으로 동작하는지 확인

### STEP2 코드 리뷰 반영 추가 수정 사항
기존 코드는 전체 상품 리스트를 받은 후 페이지에 해당하는 개수만큼 서브리스트 추출
-> 이를 애초에 Service 레이어에서 시작과 끝을 계산하고 그 값을 통해 Repository 레이어에서 해당하는 부분만 DB (또는 메모리)에서 읽어와 반환하도록 수정
관리자 페이지 목록 조회에서도 페이지 번호 생성 및 클릭으로 이동 방식에서 페이지 번호 입력으로 이동하는 방식으로 수정