# Gift-product
## STEP02: 상품 조회 및 관리 화면 구현

### 상품의 구성 요소
- ID: 등록한 순서대로 상품 ID 생성
- 이름: 상품의 이름
- 가격: 상품의 가격
- 이미지: URL을 통한 이미지 저장

### 관리자 화면 구성
- TITLE: 상품 목록
- 상품 추가: 해당 글자를 클릭하면 상품 추가 페이지로 이동(product_form)
- 상품의 목록(전체 상품 목록 조회)
  - ID / 이름 / 가격 / 이미지 / 관리(수정 및 삭제)

### API 설계
- 상품 조회
  - 전체 상품 조회 기능
  - /admin/products
  - GET
  - Return: admin/product_list로 이동
- 상품 등록
  - 상품 추가 글을 눌러서 이동
  - 상품 등록(이름, 가격, 이미지 URL)
  - 등록 버튼을 통하여 저장
  - 목록 페이지로 바로 이동 가능
  - /admin/products/new
  - GET
  - Return: admin/product_form으로 이동

- 상품 수정 페이지
  - 전체 상품 목록 페이지(product_list)에서 각 목록 중 수정 버튼 통하여 수정 페이지 이동
  - 원래 상품 값에서 수정 가능
  - /admin/products/{id}/edit
  - GET

- 상품 정보 수정
  - 수정 페이지에서 상품의 정보를 수정 후 수정 버튼을 누르면 상품의 정보가 수정됨
  - /admin/products/{id}
  - POST
  - Return: admin/product_form으로 이동(리다이렉트)
  - 
- 상품 삭제
  - 삭제 버튼 눌러서 상품의 정보 삭제
  - /admin/products/{id}/delete
  - POST
  - Return: 상품의 정보를 삭제 후 admin/product_form으로 이동(리다이렉트)