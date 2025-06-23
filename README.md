# spring-gift-product


#### API 명세서

- 상품 API

| URL | Method | 기능 | 설명                   |
|--------------|--------|-----------|----------------------|
| /api/products | POST |  물품 등록 | 새 물품을 등록한다.          |
| /api/products/{id} | GET | 물품 단건 조회 | id 값으로 물품 하나를 조회한다.  |
| /api/products | GET |  물품 목록 조회 | 모든 물품을 조회한다. |
| /api/products/{id} | PATCH | 물품 수정 | id 값에 매핑된 물품 하나를 수정한다. |
| /api/products/{id} | DELETE | 물품 삭제 | id 값에 매핑된 물품 하나를 삭제한다. |


#### 커밋 컨벤션


| type | meaning            |
| ----- |--------------------|
| feat | 기능 추가              |
| fix | 버그 수정              |
| docs | 문서 변경 (주석, README) |
| style | format 변경          |
| refactor | 리팩토링               |
| test | 테스트 코드 추가/수정       |
| chore | 유지보수 작업            |