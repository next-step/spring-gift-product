# spring-gift-product


## 1단계 구현


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


## 2단계 구현

0. 기능을 구현하기 전 README.md에 구현할 기능 목록을 정리해 추가한다.
1. 상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.
2. Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다.
3. HTML 폼 전송 등을 이용한 페이지 이동을 기반으로 한다.


### 파일 구조

- Product : 상품 객체 구현
- Productform.html : 상품 추가 form
- Products.html : 첫 화면. 상품 전체 조회
- ProductController : 상품 추가,수정,삭제,조회 구현

### ProductController 함수 구분
create, createForm : 상품 추가  
edit, editForm : 상품 수정  
delete : 상품 삭제


## 3단계 구현

0. 기능을 구현하기 전 README.md에 구현할 기능 목록을 정리해 추가한다.
1. 자바 컬렉션 프레임워크를 사용하여 메모리에 저장하던 상품 정보를 데이터베이스에 저장
2. 메모리에 저장하고 있던 모든 코드를 제거하고 H2 데이터베이스를 사용하도록 변경
3. 사용하는 테이블은 애플리케이션이 실행될 때 구축

### 추가된 파일
- resources/db/Schema.sql : 테이블 정의
- resources/db/ExmapleData.sql : 기본 데이터, 샘플 데이터 정의

### 사용 도구
- jdbcClient 사용  