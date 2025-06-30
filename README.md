# spring-gift-product
***
## Step 1 : API 명세(RestController) 
| 기능    | Method | URL                       |request|response| 상태코드     |
|-------|-------|---------------------------|----|----|----------|
| 상품 조회 | GET| /api/products/{productId} |요청 param|상품 정보| 200:정상조회 |
| 상품 추가 |POST| /api/products             |요청 body|등록 정보| 201:정상추가 |
| 상품 수정 |PUT| /api/products/{productId} |요청 body|수정 정보| 200:정상수정 |
| 상품 삭제 |DELETE| /api/products/{productId} |요청 param|X| 204:정상삭제 |

## Step 2 : View Controller 명세
| 기능    | Method | URL            |응답 페이지   |
|-------|-------|------------------|----------|
| 상품 목록 조회 | GET| / || index.html|
| 상품 단건 조회 |GET| /products/{id} |detail.html |
| 상품 추가 |GET+POST| /product/news + /products      |form.html + redirect:/|
| 상품 수정 |GET+POST| /products/{id} |update.html + redirect:/products/{id} |
| 상품 삭제 |GET| /products/{id}/delete | redirect:/ |

## Step 3 : H2 DB
- H2 DB 사용 설정
- Product schema 및 data.sql 설정
- 상품정보 : 자바 컬렉션 기반 -> H2 DB (Repository 구현)