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

| 기능           | URL                                          | 설명                                 |
|----------------|----------------------------------------------|--------------------------------------|
| 상품 목록 조회 | `/api/admin/products`                        | 모든 상품 리스트를 HTML 테이블로 표시 |
| 상품 등록 폼   | `/api/admin/products/new`                    | 새 상품 정보를 입력할 수 있는 폼 제공 |
| 상품 등록 처리 | `POST /api/admin/products`                   | 입력받은 상품 정보를 저장            |
| 상품 수정 폼   | `/api/admin/products/{id}/edit`              | 특정 상품 정보를 수정할 수 있는 폼 제공 |
| 상품 수정 처리 | `POST /api/admin/products/{id}/edit`         | 수정된 정보 저장                     |
| 상품 삭제      | `POST /api/admin/products/{id}/delete`       | 상품 삭제 수행                        |

그리고 요청할 경우에 상품명, 가격,이미지 URL중 하나라도 누락된다면 해당 페이지를 다시 보여주도록 구현을 했다.
사실 에러 페이지를 출력할 수도 있으나 개인적인 생각으로는 그냥 다시 입력할 수 있게 페이지를 보여주는 것이 사용자 입장에서 더 편할 것 같다고 판단해 이와 같이 구현했다.



### 디렉토리 구조 (step2에서 추가된 부분만 언급함)
src
├── controller
│ └── AdminProductController.java
├── templates
│ └── admin/products/
│ ├── list.html
│ ├── new.html
│ └── edit.html

