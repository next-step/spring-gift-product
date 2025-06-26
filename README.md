# spring-gift-product

---

### API 설계

| 메소드    | URL               | request          | response         | 기능     |
|--------|-------------------|------------------|------------------|--------|
| POST   | /api/products     | CreateProductDto | 201(created)     | 생성     |
| GET    | api/products/{id} | -                | ProductDto       | 단건조회   |
| GET    | api/products      | page-parameter   | List<ProductDto> | 전체조회   |
| PATCH  | api/products/{id} | UpdateProductDto | ProductDto       | 일부수정   |
| DELETE | api/products/{id} | -                | 204(no-content)  | 단건조회   |

---

### 구현 기능 목록

- ProductCollector 구현
- ProductCollector 테스트 작성
- 상품 생성 기능 구현
- 상품 조회 기능 구현
- 상품 수정 기능 구현
- 상품 삭제 기능 구현
- API 테스트 작성

### step1 개선 목록
- Validator 패턴 적용 및 예외처리 응답 코드 개선
- ProductRepository(ProductCollector) 동시성 제어 ConcurrentHashMap 적용
- Product 도메인 객체 캡슐화, 불변객체로 변경, builder 패턴 적용
- controller 통합 테스트 (CRUD 정상 작동 시나리오) 구현

### step2 구현 기능 목록
- 관리자 화면(홈) 구현
- 상품 생성 화면 구현
- 단일 상품 조회, 수정 화면 구현