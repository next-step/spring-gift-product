# spring-gift-product

## step1 구현 기능
- 상품 도메인 설계
- layered architecture - repository, service, controller 구조 생성
- 각 repository, service, controller 의존성 주입, 메모리 DB 생성
- 상품 추가 API 구현, DTO 생성
- 상품 조회 API 구현, DTO 생성
- 상품 수정 API 구현, DTO 생성
- 상품 삭제 API

## step2 구현 기능
- step1 PR 피드백 반영하여 리팩토링 진행
- 상품 조회 폼 구현
- 상품 추가 폼 구현
- 상품 수정 폼 구현
- 상품 삭제 폼 구현

## step3 구현 기능
- step2 PR 피드백 반영하여 리팩토링 진행
- validation 유효성 검증 도입
- 관리자 페이지에도 BindingResult 사용하여 유효성 검증 진행
- ControllerAdvice 사용하여 글로벌 예외 처리
- h2 db 세팅
- h2 db 적용
- service 코드의 테스트 코드 작성
