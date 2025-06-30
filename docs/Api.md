## 📦 상품 관리 API

### 상품 생성

`Post /api/products`

**Request**

```json
{
  "name": "아이스 카페 아메리카노 T",
  "price": 4500,
  "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
}
```

**Response**

```json
{
  "statusCode": 201,
  "responseMessage": "상품 생성 성공",
  "data": {
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
  }
}
```

### 상품 목록 조회

`Get /api/products`

**Response**

```json
{
  "statusCode": 200,
  "responseMessage": "상품 목록 조회 성공",
  "data": [
    {
      "id": 8146027,
      "name": "아이스 카페 아메리카노 T",
      "price": 4500,
      "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
    },
    {
      "id": 8146028,
      "name": "핫 카페 라떼",
      "price": 4800,
      "imageUrl": "https://example.com/image2.jpg"
    }
  ]
}
```

### 단건 상품 조회

`GET /api/products/{id}`

**Response**

```json
{
  "statusCode": 200,
  "responseMessage": "상품 조회 성공",
  "data": {
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
  }
}
```

### 상품 수정

`PUT /api/products/{id}`

**Request**

```json
{
  "name": "아이스 카페 아메리카노 T (업데이트됨)",
  "price": 4600,
  "imageUrl": "https://st.kakaocdn.net/product/gift/product/updated_image.jpg"
}
```

**Response**

```json
{
  "statusCode": 200,
  "responseMessage": "상품 수정 성공",
  "data": {
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T (업데이트됨)",
    "price": 4600,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/updated_image.jpg"
  }
}
```

### 상품 삭제

`DELETE /api/products/{id}`

**Response**

```json
{
  "statusCode": 204,
  "responseMessage": "상품 삭제 성공",
  "data": null
}
```