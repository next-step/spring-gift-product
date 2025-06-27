# Step 2 - 관리자 화면

# Thymeleaf 기반 상품 관리자 화면 HTTP API

## - 상품 조회

### [GET] /admin/products?page=0&size=10&sort=name,asc&categoryId=1 

상품 조회 폼 렌더링 

return "admin/product/list";

전체 상품을 페이지 단위로 조회한다.

상품 ID 또는 이름으로 특정 상품 검색이 가능하다. 

stream().filter() 와 matchesKeyword() 메소드 이용

---

## - 상품 생성

### [GET] /admin/products/new 

상품 등록 폼 렌더링 

return "admin/product/form";

빈 ProductRequest() 객체를 생성하여 모델에 담는다.

isNew 를 이용하여 등록, 수정 폼을 구분한다. (isNew = true)


### [POST] /admin/products 

상품 등록 처리

ProductService.create() 메소드를 이용하여 상품을 등록한다.

상품 등록 후 /admin/products 로 redirect

return "redirect:/admin/products";

---

## - 상품 수정

### [GET] /admin/products/{productId}/edit 

상품 수정 폼 렌더링

return "admin/product/form";

상품 ID 를 기반으로 상품의 정보를 불러와서 수정 폼에 렌더링한다.

isNew 를 이용하여 등록, 수정 폼을 구분한다. (isNew = false)

### [POST] /admin/products/{productId}/edit

상품 수정 처리

ProductService.update() 메소드를 이용하여 상품을 수정한다.

상품 수정 후 /admin/products/ 로 redirect

return "redirect:/admin/products";

---

## - 상품 삭제

### [DELETE] /admin/products/{productId}/delete 

상품 삭제 처리

ProductService.delete() 메소드를 이용하여 상품을 삭제한다.

상품 삭제 후 /admin/products/ 로 redirect

return "redirect:/admin/products";



