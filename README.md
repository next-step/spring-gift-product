# spring-gift-product

## 구조
### Product Entity  
getter/setter/프로퍼티 사용 X (DTO, Comparator를 위해서 getter만 부분 사용)

외부에서 상태를 꺼내 조작하지 않고, 객체에게 메시지를 보내 행동을 유도하도록 설계 (update메소드)

유효성 검사도 객체 내에서 진행 (validate)

### Repository 
Map<Long, domain.Product> 기반 메모리 저장소

Product의 ID는 Repository는 저장소에서 할당한다. (ID 생성 책임)

### Service 
비즈니스 로직

domain.Product create

domain.Product getById, getAllByPage, getComparator

domain.Product update

domain.Product delete

예외처리 Service 계층에서 수행

### Controller
요청 받아서 서비스 계층으로 전달
HTTP Status Code 반영 

## 상품 HTTP API
### 상품 조회   
    [GET] /api/products/{productId} productId 기반 특정 상품 조회

    [GET] /api/products?page=0&size=10&sort=name,asc&categoryId=1 페이지 단위 모든 상품 조회

### 상품 생성
    [POST] /api/products 새로운 상품 등록
    
### 상품 수정
    [PUT] /api/products/{productId} productId 기반 특정 상품 수정

### 상품 삭제
    [DELETE] /api/products/{productId} productId 기반 특정 상품 삭제

