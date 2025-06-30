# spring-gift-product

## Step1 구현 기능 목록
- `POST /api/products`를 사용하여 신규 상품 등록
- `GET /api/products/{id}`를 사용하여 특정 상품 상세내용 조회
- `GET /api/products`를 사용하여 상품 목록을 조회
- `PATCH /api/products/{id}`를 사용하여 기존 상품 수정
- `DELETE /api/products/{id}`를 사용하여 기존 상품 삭제

## Step2 구현 기능 목록
- `GET /admin/products`를 통한 상품 목록 열람
- `GET /admin/products/new`를 통한 상품 단건 생성 Form 열람
- `POST /admin/products`를 통한 상품 단건 생성 요청
- `GET /admin/products/{id}`를 통한 상품 단건 조회 및 수정 Form 열람
- `PUT /admin/products/{id}`를 통한 상품 단건 수정 요청
- `DELETE /admin/products/{id}`를 통한 상품 단건 삭제 요청

## Step2 -> Step3 리팩토링 내용
- Repo 레이어의 상품삭제 반환값을 void가 아닌 Optional로 변경
  - 목적 : 상품삭제 정상처링 여부를 Service 레이어에 전달
  - 동반된 조치 : Service 레이어에서 해당 메소드 호출부 수정 및 Exception 처리
- Repo 레이어의 상품수정 메소드 2개를 단일 Optional 반환 메소드로 통일
  - 목적 : 기존 일부수정(updateProduct)과 전체수정(덮어쓰기: putProductById)를 통일시켜 유사한 역할을 가진 메소드 최소화하고, 동시성 문제 해결
  - 동반된 조치
    1. Product 클래스의 AllArgsConstructor 생성
    2. Service 레이어에서 해당 메소드 호출부 수정 및 Exception 처리
- Service 레이어의 상품수정 메소드 2개의 이름 변경
  - 목적 : 일부수정/전체수정 메소드의 이름 중 서술어 부분을 update로 통일시켜 메소드 간 공통점/차이점 명확화
  - 동반된 조치 : Controller 레이어에서 해당 메소드 호출부 수정