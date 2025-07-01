package gift.product.dto;


import jakarta.validation.constraints.*;


public record ItemRequest(

	@NotBlank
	@Size(max = 15)
	@Pattern(
		regexp = "^[A-Za-z가-힣0-9()\\[\\]\\+\\-\\&/_]+$",
		message = "영문·숫자 및 한글, 그리고 다음 특수기호 ()[]+-&/_ 만 허용합니다."
	)
	String name,

	@NotNull @Min(value = 0, message = "가격은 음수일 수 없습니다.")
	Integer price,

	@NotBlank @Size(max = 1000, message = "url 링크는 1000자 이하여야합니다.")
	String imageUrl) {
}
