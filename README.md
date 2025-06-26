# spring-gift-product


------
### STEP1


GET /api/products 상품 조회

GET /api/products/{id} 특정 상품 조회

POST /api/products 상품 추가

PUT /api/products/{id} 상품 수정

DELETE /api/products/{id} 상품 삭제

-----

## Step 2 – 관리자 페이지 기능 구현 (Spring MVC + Thymeleaf)

**관리자가 직접 웹 브라우저에서 상품을 등록/수정/삭제할 수 있는 기능을 구현했습니다.**

### 기능 요약
| 기능             | URL                                    | 설명                                      |
|------------------|----------------------------------------|-------------------------------------------|
| 상품 목록 조회    | `/admin/products`                      | 모든 상품 리스트를 HTML 테이블로 표시       |
| 상품 상세 조회    | `/admin/products/{id}`                 | 특정 상품의 상세 정보를 확인 가능          |
| 상품 등록 폼      | `/admin/products/new`                  | 새 상품 정보를 입력할 수 있는 폼 제공       |
| 상품 등록 처리    | `POST /admin/products`                 | 입력받은 상품 정보를 저장                  |
| 상품 수정 폼      | `/admin/products/{id}/edit`            | 특정 상품 정보를 수정할 수 있는 폼 제공     |
| 상품 수정 처리    | `POST /admin/products/{id}/edit`       | 수정된 정보 저장                           |
| 상품 삭제         | `POST /admin/products/{id}/delete`     | 상품 삭제 수행 

그리고 요청할 경우에 상품명, 가격,이미지 URL중 하나라도 누락된다면 해당 페이지를 다시 보여주도록 구현을 했다.
사실 에러 페이지를 출력할 수도 있으나 개인적인 생각으로는 그냥 다시 입력할 수 있게 페이지를 보여주는 것이 사용자 입장에서 더 편할 것 같다고 판단해 이와 같이 구현했다.


### ⚠️ 사용자 정의 에러 페이지 구현

아래와 같이 HTTP 에러 코드에 대한 커스텀 에러 페이지를 구현하였다.

| 에러 코드 | 파일명          | 설명                                      |
|-----------|------------------|-------------------------------------------|
| 400       | `templates/error/400.html` | 잘못된 요청이 발생했을 경우 보여지는 페이지        |
| 404       | `templates/error/404.html` | 존재하지 않는 경로 또는 자원이 요청되었을 때 표시됨 |
| 500       | `templates/error/500.html` | 서버 내부 오류가 발생했을 경우 표시되는 페이지     |

Spring Boot의 기본 Whitelabel 에러 페이지를 대체하여 사용자에게 더 친절한 메시지를 제공하도록 설정하였다.



### 디렉토리 구조 (step2에서 추가된 부분만 언급함)
src
├── controller
│   └── AdminProductController.java
├── templates
│   ├── admin/products/
│   │   ├── list.html
│   │   ├── new.html
│   │   ├── edit.html
│   │   └── detail.html
│   └── error/
│       ├── 400.html
│       ├── 404.html
│       └── 500.html

## Step 3 – 데이터베이스 적용 (H2 + JDBC)

**기존 메모리 저장 방식을 제거하고, H2 데이터베이스를 이용해 상품 정보를 영속적으로 저장하도록 변경했다.**

### 기능 요약

- `schema.sql`, `data.sql`을 통해 애플리케이션 실행 시 DB 자동 초기화
- 모든 상품 CRUD 작업은 실제 데이터베이스와 연동하여 처리
- `JdbcTemplate`, `SimpleJdbcInsert`를 사용하여 SQL 기반 Repository 구현
- 메모리 저장소 관련 코드는 완전히 제거

### 설정 파일

`application.properties` 설정:
```properties
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:sql/schema.sql
spring.sql.init.data-locations=classpath:sql/data.sql

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### 디렉토리 구조 (step3에서 추가된 부분만 언급함)
src
├── repository
│   └── ProductRepository.java
├── resources
│   └── sql/
│       ├── schema.sql
│       └── data.sql
