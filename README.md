# Spring Gift Product

## API 명세서

| **Method** | **Path** | **Request**                                                         | **Response**                                                                        | **Description** |
| --- | --- |---------------------------------------------------------------------|-------------------------------------------------------------------------------------| --- |
| **`POST`** | /api/products | {"id": Long, "name": String, "price": Integer, "imageUrl": String } | 201 **`Created`**                                                                   | 상품 추가 |
| **`GET`** | /api/products |                                                                     | 200 **`OK`** [{ "id": Long, "name": String, "price": Integer, "imageUrl": String }] | 상품 전체 조회 |
| **`GET`** | /api/products/{id} |                                                                     | 200 **`OK`** { "id": Long, "name": String, "price": Integer, "imageUrl": String  }  | 상품 단일 조회 |  | **`PATCH`** | /api/products/{id} | { "name": String, "price": Integer, "imageUrl": String } | 200 **`OK`** | 상품 수정 |
| **`DELETE`** | /api/products/{id} |                                                                     | 204 **`No content`** | 상품 삭제 |