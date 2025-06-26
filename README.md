# spring-gift-product

### 요구사항

0. 기능을 구현하기 전 README.md에 구현할 기능 목록을 정리해 추가한다.
1. 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
2. HTTP 요청과 응답은 JSON 형식으로 주고받는다.
3. 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

### ProductController.java
상품 조회,추가,수정,삭제 기능 담당
- 상품 조회 : getProducts()
- 상품 추가 : addProducts()
- 상품 수정 : updateProducts()
- 상품 삭제 : deleteProduct()

### Application.java
실행 main (springBootApplication)

### Product.java

Product 정의

- id, name, price, imageURL로 구성