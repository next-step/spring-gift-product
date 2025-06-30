# Step 3 - 데이터베이스 적용

자바 컬렉션 프레임워크 HashMap 을 사용하여 

메모리에 저장하던 상품 정보를 데이터베이스에 저장한다.

---

### H2 데이터베이스를 이용한다.
    인메모리 DB 로, 어플리케이션에 내장되어 빠르다.
    별도 설치 필요 X 

    의존성 추가 필요 (build.gradle 파일)
    runtimeOnly 'com.h2database:h2'

    DB 설정 추가
    # h2-console 활성화 여부
    spring.h2.console.enabled=true
    # db url
    spring.datasource.url=jdbc:h2:mem:test
---

### JDBC Template 을 이용한다.
    Spring 이 제공하는 JDBC 접근을 단순화한 도구
    
    의존성 추가 필요 (bulid.gradle 파일)
    implementation 'org.springframework.boot:spring-boot-starter-jdbc'
    

---

### HashMap -> JdbcTemplate 리팩토링 진행
    기존 메모리 저장 방식에서 
    데이터베이스 저장 방식으로 리팩토링
    ProductRepository 수정

