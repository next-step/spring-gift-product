# 상품 조회 API 명세서

## 구현 목록

| URL                         | 메서드    | 기능       | 설명                |
|:----------------------------|:-------|----------|-------------------|
| `/api/products/`            | GET    | 다건 상품 조회 | 모든 상품의 정보를 조회합니다. |
| `/api/products/{productId}` | GET    | 단건 상품 조회 | 특정 상품의 정보를 조회합니다. |
| `/api/products/`            | POST   | 단건 상품 생성 | 새로운 상품을 생성합니다.    |
| `/api/products/{productId}` | PUT    | 단건 상품 수정 | 특정 상품의 정보를 수정합니다. |
| `/api/products/{productId}` | DELETE | 단건 상품 삭제 | 특정 상품을 삭제합니다.     |

## 요청 및 응답 예시

### 1. 다건 상품 조회
#### 요청

페이지와 크기를 지정하여 상품 목록을 조회할 수 있습니다. 기본적으로 첫 페이지(0번 페이지)와 한 페이지에 5개의 상품을 조회합니다.

```http
GET /api/products/ HTTP/1.1?page=0&size=5
```

#### 요청 파라미터

| 이름         | 기본값        | 설명                |
|:-------------|:-------------|-------------------|
| `page`       | `0`           | 조회할 페이지 번호 (0부터 시작) |
| `size`       | `5`           | 한 페이지에 표시할 상품 수      |


#### 응답

contents 필드에는 상품 목록이 포함되며, 각 상품은 다음과 같은 정보를 포함합니다:
+ `id`: 상품의 고유 ID
+ `name`: 상품 이름
+ `price`: 상품 가격

페이지 정보는 다음과 같이 포함됩니다:
+ `page`: 현재 페이지 번호
+ `size`: 페이지당 상품 수
+ `totalElements`: 전체 상품 수
+ `totalPages`: 전체 페이지 수

##### 응답 예시
```http

```json
{
  "contents": [
    {
      "id": 1,
      "name": "Product 01",
      "price": 1000,
      "imageUrl": "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8c25lYWtlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60\""
    },
    {
      "id": 2,
      "name": "Product 02",
      "price": 2000,
      "imageUrl": "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8c25lYWtlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60\""
    },
    {
      "id": 3,
      "name": "Product 03",
      "price": 3000,
      "imageUrl": "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8c25lYWtlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60\""
    },
    {
      "id": 4,
      "name": "Product 04",
      "price": 4000,
      "imageUrl": "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8c25lYWtlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60\""
    },
    {
      "id": 5,
      "name": "Product 05",
      "price": 5000,
      "imageUrl": "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8c25lYWtlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60\""
    }
  ],
  "page": 0,
  "size": 5,
  "totalElements": 10,
  "totalPages": 2
}
```

### 2. 단건 상품 조회

#### 요청

특정 상품의 정보를 조회합니다. 상품 ID를 URL 경로에 포함시켜 요청합니다.

```http
GET /api/products/1 HTTP/1.1
```

#### 응답

상품의 상세 정보가 포함된 응답을 반환합니다. 

##### 응답 예시
```json
{
  "id": 1,
  "name": "Product 01",
  "price": 1000,
  "imageUrl": "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8c25lYWtlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60\""
}
```

### 3. 단건 상품 생성

#### 요청
새로운 상품을 생성합니다. 상품의 이름과 가격을 요청 본문에 포함시켜 POST 요청을 보냅니다.

```http
POST /api/products/ HTTP/1.1
Content-Type: application/json

{
  "name":"test1",
  "price": 10000,
  "imageUrl": "www.example/test01"
}
```

#### 응답
상품 생성이 성공적으로 완료되면 생성된 상품의 정보가 포함된 응답을 반환합니다.

##### 응답 예시
```json
{
  "id": 11,
  "name": "test1",
  "price": 10000,
  "imageUrl": "www.example/test01"
}
```

### 4. 단건 상품 수정

#### 요청
특정 상품의 정보를 수정합니다. 상품 ID를 URL 경로에 포함시키고, 수정할 정보를 요청 본문에 포함시켜 PUT 요청을 보냅니다.
요청 본문에 누락된 필드는 수정되지 않습니다.


```http
PUT /api/products/1 HTTP/1.1
Content-Type: application/json

{
  "name": "Updated Product",
  "price": 5000
}
```

#### 응답
상품 수정이 성공적으로 완료되면 수정된 상품의 정보가 포함된 응답을 반환합니다.

##### 응답 예시
```json
{
  "id": 1,
  "name": "Updated Product",
  "price": 5000,
  "imageUrl": "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8c25lYWtlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60\""
}
```


### 5. 단건 상품 삭제

#### 요청
특정 상품을 삭제합니다. 상품 ID를 URL 경로에 포함시켜 DELETE 요청을 보냅니다.

```http
DELETE /api/products/1 HTTP/1.1
```

#### 응답
상품 삭제가 성공적으로 완료되면 HTTP 상태 코드 204 No Content를 반환합니다. 응답 본문은 없습니다.


### 3. 에러 응답
에러가 발생할 경우, 다음과 같은 형식의 응답을 반환합니다.

#### 에러 응답 예시
잘못된 상품 ID를 요청했을 때의 예시입니다.

```json
{
  "timestamp": "2025-06-27T13:12:47.7452837",
  "message": "Id 1111에 해당하는 상품이 존재하지 않습니다.",
  "status": 404,
  "error": "Not Found",
  "path": "/api/products/1111",
  "stackTrace": "gift.service.ProductServiceImpl.lambda$getById$0(ProductServiceImpl.java:45)"
}
```
+ **필드 설명**
  + `timestamp`: 에러가 발생한 시간
  + `message`: 에러 메시지
  + `status`: HTTP 상태 코드
  + `error`: 에러 타입
  + `path`: 요청한 URL 경로
  + `stackTrace`: 에러가 발생한 코드의 스택 트레이스 (선택적, 디버깅 용도)