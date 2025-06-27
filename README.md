# spring-gift-product
# API 작성
1. 3 Layer 분리 및 데이터 저장 할 컬렉션, 형태 정의
2. 레이어 간 의존성 주입 및 Controller에 API 작성
3. Product 생성자 추가, 오타 수정 및 Service 레이어 구성
4. Repository 레이어 구성, 오타 수정 및 Product entity 불변성 제거
5. Controller 반환 코드 세분화, 제품 등록 할 때 유효성 검사 추가
6. 코드 피드백 반영

    findProductById Repository에서 예외 반환하지 않고 Optional로 감싼 후 Service에서 예외 처리하도록 수정 
    
    Product entity의 id를 0으로 만든 후 setId로 후처리하던 방식에서 Repository에서 새로 만드는 방식으로 변경
    
    Product entity 다시 record 형식으로 바꿔서 불변성 유지
    
    Controller 레이어의 findAllProducts ResponseEntity 사용하도록 변경
    
    유효성 검사 Service 레이어가 아닌 Controller 레이어에서 하도록 변경, @Valid 사용하여 코드 중복 줄임

#  관리자 페이지 작성

1. Product entity에 imageUrl 필드 추가에 따른 리팩토링
2. ViewController 생성 및 리스트 페이지, 삭제 버튼 구현
3. 생성 페이지, 수정 페이지 구현
4. 코드 피드백 반영

    ProductViewController 의 역할이 애매하여 AdminController로 역할 변경

    Repository는 무조건 Product만 입력 받고 반환하도록 변경. 이에 따른 RequestDto -> Entity 생성자 구현

    Service에서 ResponseStatusException로 응답코드 반환 하지 않고 @RestControllerAdvice에서 처리하도록 변경