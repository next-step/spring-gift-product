package gift.product.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record ItemRequest(

	@NotBlank
	String name,

	@NotNull @Min(0)
	Integer price,

	@NotBlank @Size(max = 1000)
	String imageUrl) {
}
