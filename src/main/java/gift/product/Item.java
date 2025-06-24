package gift.product;


public class Item {

	private Long id;

	private String name;

	private Integer price;

	private String imageUrl;

	public Item(String name, Integer price, String imageUrl) {
		this.id = nextId++; // 생성자에서 id 저장
		this.name = name;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	// auto increment 구현용 정적변수
	private static long nextId = 1;


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
