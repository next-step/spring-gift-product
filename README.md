# 📝 소개

- - -
카카오테크 캠퍼스 백엔드 step2 과정으로 카카오톡의 선물하기 기능을 클론 코딩하는 것을 목적으로 한다.

# 📖 API 명세

- - -

## 상품 관리 API

| URL                       | 메서드    | 기능            | 설명           |
|---------------------------|--------|---------------|--------------|
| /api/products             | POST   | 상품 생성         | 새로운 상품 등록    |
| /api/products             | GET    | 상품 조회 (목록 조회) | 상품 목록 조회     |
| /api/products/{productId} | GET    | 상품 조회 (단건 조회) | 특정 상품의 정보 조회 |
| /api/products/{productId} | PUT    | 상품 수정         | 기존 상품 정보 수정  |
| /api/products/{productId} | DELETE | 상품 삭제         | 기존 상품 삭제     |

[자세한 API 문서 보러 가기](Api.md)