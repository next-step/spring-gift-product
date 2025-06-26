# spring-gift-product

---

## 기능 요구사항
### 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API 구현 
- HTTP 요청과 응답은 JSON 형식으로 주고 받는다
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임 워크를 사용해 메모리에 저장한다. 

| URL | 메서드 | 기능 | 설명 |
|-----|--------|------|------|
| `/api/products` | POST | 상품 생성 | 새 상품을 등록한다. |
| `/api/products/{productId}` | GET | 상품 조회 | 특정 상품의 정보를 조회한다. |
| `/api/products/{productId}` | PUT | 상품 수정 | 기존 상품의 정보를 수정한다. |
| `/api/products/{productId}` | DELETE | 상품 삭제 | 특정 상품을 삭제한다. |
| `/api/products?page=0&size=10&sort=name,asc&categoryId=1` | GET | 상품 목록 조회 (페이지네이션 적용) | 모든 상품의 목록을 페이지 단위로 조회한다. |


## Step 2 서버 사이드 랜러링(타임 리프)

### 상품 관리
<img src="https://private-user-images.githubusercontent.com/138632648/459282427-dcc0b548-c6f4-42df-bf36-9c7b5c26cff3.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTA5MjM3NzMsIm5iZiI6MTc1MDkyMzQ3MywicGF0aCI6Ii8xMzg2MzI2NDgvNDU5MjgyNDI3LWRjYzBiNTQ4LWM2ZjQtNDJkZi1iZjM2LTljN2I1YzI2Y2ZmMy5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwNjI2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDYyNlQwNzM3NTNaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1hMjA3ZmEwNGM0ODdmMzI3ZWVlOGYwNjFhN2MzZDJkOTNmODdiMTRmM2YyMWI3NmJjNTZkYTMyZTdlNmI3YzQ4JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.2bTX2QYhIrlqfATpxRpHqxo9FKLzc3HGkP_zgCbd5Bg" alt="상품 관리 화면 " width="600"/>
---
### 등록
<img src="https://private-user-images.githubusercontent.com/138632648/459280764-2e1a32ec-b62f-4298-ab25-3f30be97a344.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTA5MjM1NjMsIm5iZiI6MTc1MDkyMzI2MywicGF0aCI6Ii8xMzg2MzI2NDgvNDU5MjgwNzY0LTJlMWEzMmVjLWI2MmYtNDI5OC1hYjI1LTNmMzBiZTk3YTM0NC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwNjI2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDYyNlQwNzM0MjNaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0xMzI1M2JkNjc2YmNkNGU2ODk3YWJiY2E3MDkyNGViYzYxNDNjNDFjZjczZDM0NzcyZTNjOGM0YWQ1YzgyZDE4JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.-NuJHWn8y28hp4jGqSlYMm0aynJp8Qbfpsv3BV3YOeM" alt="상품 등록 화면 " width="600"/>

---
### 수정
<img src="https://private-user-images.githubusercontent.com/138632648/459280854-0e4f9391-c2bc-4df7-b34b-040685c0e035.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTA5MjM1NjMsIm5iZiI6MTc1MDkyMzI2MywicGF0aCI6Ii8xMzg2MzI2NDgvNDU5MjgwODU0LTBlNGY5MzkxLWMyYmMtNGRmNy1iMzRiLTA0MDY4NWMwZTAzNS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwNjI2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDYyNlQwNzM0MjNaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT02OTA5NzM3MjdhZTY2Y2E5NmIzOTg2YTdlMWIzN2M4NmJhNzhkMjZjMzI3ZTViYzQ4MmQ4MDhhODBiZDVjMzU5JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.YkhQDYxxIqUBDImh9YrmmiauZGQlVXaRqxOxpPDygwA" alt="상품 수정화면" width="600"/>
---
### 검증
<img src="https://private-user-images.githubusercontent.com/138632648/459280881-88092cdd-04f4-4b1b-b49b-13909a596935.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTA5MjM1NjMsIm5iZiI6MTc1MDkyMzI2MywicGF0aCI6Ii8xMzg2MzI2NDgvNDU5MjgwODgxLTg4MDkyY2RkLTA0ZjQtNGIxYi1iNDliLTEzOTA5YTU5NjkzNS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwNjI2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDYyNlQwNzM0MjNaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1hMjQzMzU5YWRkMzQ1N2MwZTYwYWJiMWRjOGI4MzU3NjZlNTVhNmI4ZTU2NjVjZjcxMzQ2ZTdkMTQ0MzYwZjk3JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.3RAFTAzWm8vHyNh_Vow3B6fGlPJHDBRDD3AjGPgogGQ" alt="상품 관리 화면" width="600"/>

