# spring-gift-product

## 구현할 기능 목록

- [x] 상품 조회 API 구현
- [x] 상품 등록 API 구현
- [ ] 상품 수정 API 구현
- [ ] 상품 삭제 API 구현
- [ ] 상품 목록 페이지네이션 조회 API 구현


## 구현 기능 상세

### ✅ 상품 조회 API (`GET /api/products/{id}`)
- 상품 ID를 통해 개별 상품 정보를 조회할 수 있는 API
- 응답 형식: JSON
- 응답 예시:
```json
{
  "id": 1,
  "name": "아이스 아메리카노",
  "price": 4500,
  "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_..."
}
```
### ✅ 상품 등록 API (`POST /api/products`)
- 새로운 상품을 등록하는 API
- 요청 Body: JSON 형식의 상품 정보 (name, price, imageUrl)
- 응답: 생성된 상품 객체와 자동 생성된 id
- 응답 예시:
```json
{
  "id": 2,
  "name": "뜨거운 아메리카노",
  "price": 4000,
  "imageUrl": "https://st.kakaocdn.net/product/gift/product/20240101120000_hotamericano.jpg"
}
```