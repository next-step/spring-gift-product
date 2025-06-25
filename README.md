## 🛒 1단계 - 상품 API

Spring Boot 기반의 간단한 상품 관리 API입니다.  
상품을 **조회, 등록, 수정, 삭제**할 수 있는 RESTful API를 제공합니다.

---

### ✅ 구현할 기능 목록

#### 📌 1. 상품 조회
- [ ] 전체 상품 목록 조회 (GET `/api/products/page=0&size=10&sort=name,asc&categoryId=1`)
- [ ] 단일 상품 상세 조회 (GET `/api/products/{id}`)

#### 📌 2. 상품 등록
- [ ] 신규 상품 등록 (POST `/api/products`)
    - 요청 본문에 name, price, description 등 포함

#### 📌 3. 상품 수정
- [ ] 상품 정보 수정 (PUT `/api/products/{id}`)
    - 요청 본문에 수정할 필드 포함 (예: name, price)

#### 📌 4. 상품 삭제
- [ ] 상품 삭제 (DELETE `/api/products/{id}`)

---

### 🧱 향후 확장 고려사항
- [ ] 상품 검색 기능 (name 또는 카테고리 기준)
- [ ] 상품 품절 상태 관리
- [ ] 등록일 기준 정렬 기능
