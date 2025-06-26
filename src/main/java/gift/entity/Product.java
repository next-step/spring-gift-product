package gift.entity;

import gift.dto.RequestDto;

public class Product {

    private Long id;
    private String name;
    private String imageUrl;

    public Product(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void update(RequestDto dto) {
        if (dto.getName() != null) {
            this.name = dto.getName();
        }
        if (dto.getImageUrl() != null) {
            this.imageUrl = dto.getImageUrl();
        }
    }
}
