# spring-gift-product


## 목차

1. [1단계 - 상품 API](#step-1---상품-api)
2. [2단계 - 관리자 화면](#step-2---관리자-화면)

---

## Step 1 - 상품 API

---

#### 기능 요구 사항

- HTTP 요청과 응답은 JSON 형식으로 주고받음
- 별도 DB가 없으므로 Map을 사용하여 메모리에 저장

<br>

#### API 명세 

| URL                  | 메서드  | 기능    | 설명           |
|----------------------|------|-------|--------------|
| /api/products        | POST | 상품 생성 | 새 상품 등록      |
| /api/products/{productId} | GET  | 상품 조회 | 특정 상품 정보 조회  |
| /api/products/{productId} | PUT  | 상품 수정 | 기존 상품 정보 수정  |
| /api/products/{productId} | DELETE | 상품 삭제 | 특정 상품 삭제     |
| /api/products | GET | 상품 전체 조회 | 모든 상품 정보 조회  |

---

<br>

## Step 2 - 관리자 화면

---

#### 기능 요구 사항

상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구성

- `Thymeleaf`를 사용하여 서버 사이드 렌더링을 구현
- 상품 이미지의 경우, 파일 업로드가 아닌 URL 직접 입력
