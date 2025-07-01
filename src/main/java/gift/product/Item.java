package gift.product;


public class Item {

	private Long id;

	private String name;

	private Integer price;

	private String imageUrl;

	public Item(String name, Integer price, String imageUrl) {
		this.name = name;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public Item(Long id, String name, Integer price, String imageUrl) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
