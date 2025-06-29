# spring-gift-product

# 1단계 구현 기능 목록
- 상품 추가 'POST /api/products' 상품을 추가한다
- 상품 목록 조회 'GET /api/products' 전체 상품을 조회한다
- 상품 단건 조회 'GET /api/products/{id}' id 값에 해당하는 상품을 조회한다
- 상품 단건 수정 'PUT /api/products/{id}' id 값에 해당하는 상품의 내용을 수정한다
- 상품 단건 삭제 'DELETE /api/products/{id}' id 값에 해당하는 상품을 삭제한다

# 2단계 구현 기능 목록
- 관리자 화면 생성 
  - 'GET /admin/boards' 관리자 페이지에 접속한다
- 관리자 화면에 상품 조회 기능 구현 
- 관리자 화면에 상품 추가 기능 구현 
  - 관리자 페이지에서 상품 등록 버튼을 클릭해 'GET /admin/boards/add' 상품 추가 페이지에 접근한다
- 관리자 화면에 상품 수정 기능 구현
  - 관리자 페이지에서 수정 버튼을 클릭해 'GET /admin/boards/update/{id}' 상품 수정 페이지에 접근한다
- 관리자 화면에 상품 삭제 기능 구현
  - 관리자 페이지에서 삭제 버튼을 클릭해 id 값에 해당하는 상품을 삭제한다

# 3단계 구현 기능 목록
- 기존 HashMap으로 저장하는 Repository Layer 구현체를 삭제하고 인메모리 DB인 H2를 사용하는 구현체를 사용하도록 변경한다
  - repository 패키지의 ProductRepositoryImpl 삭제
  - repository 패키지에 JDBC client 사용하는 ProductDao 구현