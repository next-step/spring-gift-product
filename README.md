# REST API
| URL                  | Method | Description              |
|----------------------|--------|--------------------------|
| `/api/products`      | GET    | (상품 조회) 전체 상품의 정보를 조회한다. |
| `/api/products/{id}` | GET    | (상품 조회) 특정 상품의 정보를 조회한다. |
| `/api/products`      | POST   | (상품 추가) 새 상품을 추가한다.      |
| `/api/products/{id}` | PUT    | (상품 수정) 특정 상품의 정보를 수정한다. |
| `/api/products/{id}` | DELETE | (상품 삭제) 특정 상품을 삭제한다.     |


# Admin Page
| URL                  | Method | Description                    |
|----------------------|--------|--------------------------------|
| `/admin`             | GET    | (상품 목록 조회) 전체 상품 목록을 조회한다.     |
| `/admin/add`         | GET    | (상품 등록 페이지) 상품 등록 폼을 조회한다.     |
| `/admin/add`         | POST   | (상품 등록 처리) 새 상품을 등록한다.         |
| `/admin/edit/{id}`   | GET    | (상품 수정 페이지) 특정 상품 수정 폼을 조회한다.  |
| `/admin/edit/{id}`   | POST   | (상품 수정 처리) 특정 상품의 정보를 수정한다.    |
| `/admin/delete/{id}` | POST   | (상품 삭제 처리) 특정 상품을 삭제한다.        |

# DB Apply
[O] h2 DB 설정하기
[O] 스키마 스크립트, 데이터 스크립트를 각각 schema.sql, data.sql에 작성하기
[O] 상품 정보를 Map 대신 DB에 저장하도록 Repository 클래스 수정하기