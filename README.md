# spring-gift-product

## 상품 관리 - 3단계 과제 : h2 데이터베이스 연결

- 기존에 사용하던 임시 데이터베이스가 아니라 h2 데이터베이스를 사용하도록 리팩토링한다.
- application에서 데이터베이스에 접근하는 방식은 Jdbc를 활용한다.

### 1. h2 데이터베이스 연결

- application.properties 설정을 통해 h2 데이터베이스를 연결한다.
- data.sql, schema.sql 파일을 통해 애플리케이션 실행 때마다 실행될 sql 파일을 구현한다.
- products entity는 auto_increment를 통해 id를 자동생성한다.

### 2. JdbcTemplate을 통한 데이터베이스 접근 구현

- 왜 JbdcTemplate인가?
- ProductRepository 객체에서 JdbcTemplate을 사용하도록 구현한다.
- 사용자용 API, 관리자용 API 모두 데이터베이스 접근을 위해 공통적으로 ProductRepository를 이용하고 있다. ProductRepository에서
  JdbcTemplate를 통한 데이터베이스 접근 로직을 구현하는 게 적합하다고 생각했다.

### 3. 상품 생성 시 생성된 상품 id를 반환하도록 수정

- h2 데이터베이스 연결 전에는 상품의 id를 사용자가 생성 시에 직접 명시할 수 있었고, 사용자가 상품의 id를 모두 인지하고 있다는 가정 하에 로직을 구성했다.
- 이제 사용자와 관리자는 id를 제외한 상품 정보만 전달하고, id는 insert 시에 자동 생성된다.
- 사용자가 본인이 생성한 상품의 id를 참조할 수 있는 방법이 필요했다. 상품 생성 시에 생성된 상품 id만 반환하도록 수정했다.
- KeyHolder를 이용해 자동생성된 키값을 반환한다.

### 4. service, repository 객체마다 구현했던 인터페이스 삭제

- service, repository마다 interface를 두고, 그 interface를 구현한 구현체를 각각 두었다.
- 반환타입 때문에 일반 사용자와 관리자별로 service interface와 구현체를 따로 두어야 했는데, 그러다 보니 프로젝트 규모에 비해 구조가 복잡해지고, 추상화가 과하다는
  생각이 들었다.
- service, repository 계층 모두 interface를 두고 구현체를 구현하는 방식이 아니라 interface 없이 repository/service 객체를
  구현했다.


