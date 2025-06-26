# spring-gift-product

## step1 - 상품 API

### 📌 목표

-   상품(Product)을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
-   JSON 포맷으로 HTTP 요청 및 응답을 처리한다.
-   현재는 DB를 사용하지 않으며, 자바 컬렉션을 활용한 메모리 기반 저장소를 사용한다.
-   그러나 향후 DB 교체를 고려하여 설계를 유연하게 구성해야 한다.

## 🔨 기능 목록

1. **Product 도메인 (Immutable 객체)**

    - `Product` 객체는 자바 `record`를 활용해 불변(Immutable)으로 구현한다.

    - 객체 생성 시 다음 조건을 엄격하게 검증하며, 조건을 위반하면 `IllegalArgumentException`을 발생시킨다.

        | 필드명   | 타입      | 제약 조건                                               |
        | -------- | --------- | ------------------------------------------------------- |
        | id       | `Long`    | null일 수 있으나, 명시적 생성 시에는 null 불가 (필수값) |
        | name     | `String`  | null 또는 빈 문자열, 공백 문자만 포함할 수 없음         |
        | price    | `Integer` | null 불가, 0 이상인 정수만 가능                         |
        | imageUrl | `String`  | null 또는 빈 문자열, 공백 문자만 포함할 수 없음         |

    - **객체 생성 메서드**

        - `of(String name, Integer price, String imageUrl)` : ID 없이 생성 (ID는 null)
        - `withId(Long id, String name, Integer price, String imageUrl)` : ID 포함 생성, `id`가 null이면 예외
          발생

    - **업데이트 메서드**

        - `update(String name, Integer price, String imageUrl)` : 기존 `id` 유지, 새 필드값으로 새 `Product` 객체
          반환
        - null 또는 빈 값 필드는 기존 값 유지
        - 모든 필드가 null 또는 빈 값일 경우 예외 발생

2. **Product 저장소 (Repository)**

    - `ProductRepository` 인터페이스 정의
    - 메모리 기반 저장소(`InMemoryProductRepository`) 구현:

        - 내부에 `ConcurrentHashMap<Long, Product>`로 상품 저장
        - `AtomicLong`으로 고유 ID 자동 생성

    - CRUD 기능 제공: 저장, 수정, 삭제(단건 및 전체), 조회(단건 및 전체)
    - 수정, 삭제, 조회 시 존재하지 않는 ID에 대해 적절한 예외 처리

3. **서비스 계층 (Service)**

    - `ProductManagementService` 클래스에서 비즈니스 로직 처리
    - `ProductRequest` DTO를 이용해 입력 데이터 받음
    - 입력값 유효성 검사 수행(예: null, 빈 값 체크)
    - 존재하지 않는 상품에 대해 조회, 수정, 삭제 시 `BusinessException` 발생
    - 생성 시 유효하지 않은 데이터에 대해서도 `BusinessException` 발생
    - 도메인 객체 생성 및 업데이트를 서비스 계층에서 수행
    - 서비스 메서드: `create`, `getAll`, `getById`, `update`, `deleteAll`, `deleteById`

4. **API 계층 (Controller) \[추후 구현 예정]**

    - RESTful API 엔드포인트 제공 (예: `/products`)
    - HTTP 메서드에 따른 CRUD 매핑:

        - `POST /products`: 상품 생성
        - `GET /products`: 상품 전체 조회
        - `GET /products/{id}`: 상품 단건 조회
        - `PUT /products/{id}`: 상품 수정
        - `DELETE /products/{id}`: 상품 단건 삭제
        - `DELETE /products`: 전체 삭제

    - 요청 및 응답 모두 JSON 포맷
    - 예외 발생 시 HTTP 상태 코드 및 메시지 포함한 JSON 형태로 응답

5. **예외 처리**

    - `BusinessException`을 통한 예외 관리
    - `ErrorCode` 열거형으로 에러 종류 및 메시지 관리
    - 글로벌 예외 처리기로 예외를 JSON 형태로 변환해 클라이언트에 전달
    - 스택 트레이스를 포함하여, 추적에 용이하게 응답 구성

6. **기타**

    - 확장성과 유지보수를 고려한 인터페이스 설계 및 역할 분리
    - 불변 객체와 명확한 유효성 검사로 안정성 확보
    - 멀티스레드 환경 대응을 위한 `ConcurrentHashMap` 및 `AtomicLong` 사용

물론이야! 아래는 기존의 `README`에 이어서 붙일 수 있도록 **하위 항목으로 구조화**한 내용이야. 마크다운 형식으로 정리했어.

## step2 - Admin 페이지 (Thymeleaf 기반)

1. **구조 설계 및 구성**

-   🔹 템플릿 분리 (`Fragment`)

    -   `Thymeleaf`의 `th:fragment` 기능을 이용해 반복되는 영역(header, footer)을 분리.
    -   관리자 기능이 추가되어도 `레이아웃 변경 없이 UI 조합만으로 확장` 가능.
    -   예시 구조:

    ```
    templates/
    ├── admin/
    │   └── product-manage.html
    └── fragments/
        ├── header.html
        └── footer.html
    ```

-   🔹 정적 자산 분리 (CSS / JS)

    -   CSS, JS를 모두 `/static/` 경로에 분리하여 유지보수성 확보:

    ```
    static/
    ├── css/
    │   └── product-manage.css
    └── js/
        └── product-manage.js
    ```

    -   관리자 페이지 특화 스타일 `.admin-header`, `.admin-footer` 정의

    -   `header`는 상단 고정, `footer`는 하단 고정 처리

2. **사용자 경험 개선 (UX)**

-   🔹 선택 삭제 기능

    -   상품 목록에서 체크박스를 통해 다중 선택 후, `선택 삭제` 버튼으로 삭제 가능
    -   실수 방지를 위해 `confirm 창`으로 사용자 확인을 받고 진행

-   🔹 페이징 처리

    -   화면 하단에 최대 5개의 페이지 버튼이 보이며, 현재 페이지를 강조 표시
    -   `JS + 백엔드 페이징 API` 연동으로 동작

3. **트러블슈팅 & 최적화**

-   🛠 문제: 다수 삭제 시 I/O 과다

    -   기존: `DELETE /products/{id}` 방식으로 각 상품마다 삭제 요청
    -   결과: 클라이언트 → 서버로 **N번의 HTTP 요청** 발생
        → 네트워크 병목 및 서버 부하

-   ✅ 해결: Bulk Delete API 도입

    -   개선: `DELETE /api/products/bulk` + `@RequestBody List<Long> ids` 사용
    -   서버 내부에서 `ConcurrentHashMap.remove()`를 순차적으로 수행
    -   결과: **1회의 HTTP 요청**으로 여러 개의 상품 삭제 가능

    | 항목               | 개선 전                    | 개선 후          |
    | ------------------ | -------------------------- | ---------------- |
    | 네트워크 요청 횟수 | 상품 수만큼                | 1회              |
    | 서버 리소스 사용   | 병렬 요청 스레드 증가      | 단일 요청 처리   |
    | 코드 복잡도        | 클라이언트 fetch 병렬 처리 | 단순 리스트 전달 |

4. 구현 UI
   ![alt text](docs/image.png)
