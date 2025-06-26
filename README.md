# spring-gift-product
***
## API 명세(RestController)
| 기능    | Method | URL                       |request|response| 상태코드     |
|-------|-------|---------------------------|----|----|----------|
| 상품 조회 | GET| /api/products/{productId} |요청 param|상품 정보| 200:정상조회 |
| 상품 추가 |POST| /api/products             |요청 body|등록 정보| 201:정상추가 |
| 상품 수정 |PUT| /api/products/{productId} |요청 body|수정 정보| 200:정상수정 |
| 상품 삭제 |DELETE| /api/products/{productId} |요청 param|X| 204:정상삭제 |

## View Controller 명세
| 기능    | Method | URL            |응답 페이지   |
|-------|-------|------------------|----------|
| 상품 목록 조회 | GET| / || index.html|
| 상품 단건 조회 |GET| /products/{id} |detail.html |
| 상품 추가 |GET+POST| /product/news + /products      |form.html + redirect:/|
| 상품 수정 |GET+POST| /products/{id} |update.html + redirect:/products/{id} |
| 상품 삭제 |GET| /products/{id}/delete | redirect:/ |