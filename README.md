# spring-gift-product
## step1 상품 API("/api/products")
* 전체 조회 
* 개별 조회 ("/{id}")
* 상품 추가
* 상품 수정 ("/{id}")
* 상품 삭제 ("/{id}")
## step2 관리자 화면
* 상품 목록 조회 화면
* 상품 추가/수정 화면
* 상품 삭제
* ![상품 목록](src/main/resources/tempImg/%EA%B8%B0%EB%B3%B8.png)
* ![상품 추가/수정](src/main/resources/tempImg/%EC%83%88_%EC%83%81%ED%92%88_%EC%B6%94%EA%B0%80_%EC%88%98%EC%A0%95.png)
* ![상품 추가 후](src/main/resources/tempImg/%EC%83%81%ED%92%88_%EC%B6%94%EA%B0%80_%ED%9B%84.png)
* ![상품 삭제 후](src/main/resources/tempImg/%EC%83%81%ED%92%88_%EC%82%AD%EC%A0%9C_%ED%9B%84.png)
## step3 데이터베이스 연동
*product repository를 jdbctemplate 활용방식으로 변경함
* ![상품 추가 후 목록](src/main/resources/tempImg/jdbc-%EA%B8%B0%EB%B3%B8.png)
* ![상품 수정](src/main/resources/tempImg/jdbc-%EC%88%98%EC%A0%95.png)
* ![상품 삭제](src/main/resources/tempImg/jdbc-%EC%82%AD%EC%A0%9C.png)