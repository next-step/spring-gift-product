# spring-gift-product

## API 명세 (ProductController)

| 기능         | HTTP Method | URL                     | 요청 (Request Body)   | 응답 (Response)                                |
|--------------|-------------|-------------------------|-----------------------|------------------------------------------------|
| 상품 등록    | POST        | `/api/products`         | `ProductRequestDTO`   | `201 CREATED`, `ProductResponseDTO`            |
| 상품 수정    | PUT         | `/api/products/{id}`    | `ProductRequestDTO`   | `200 OK`, `ProductResponseDTO` 또는 `204 No Content` |
| 상품 단건 조회 | GET         | `/api/products/{id}`    | —                     | `200 OK`, `ProductResponseDTO` 또는 `204 No Content` |
| 상품 전체 조회 | GET         | `/api/products`         | —                     | `200 OK`, `List<Product>`                      |
| 상품 삭제    | DELETE      | `/api/products/{id}`    | —                     | `204 No Content`                               |

## 관리자 페이지 라우팅 (AdminController)

| 기능               | HTTP Method | URL                    | 반환 뷰 (Thymeleaf)           |
|--------------------|-------------|------------------------|-------------------------------|
| 관리자 메인 페이지 | GET         | `/admin`               | `admin/index.html`            |
| 상품 등록 페이지   | GET         | `/admin/create`        | `admin/createProduct.html`    |
| 상품 수정 페이지   | GET         | `/admin/update/{id}`   | `admin/updateProduct.html`    |
 