package gift.entity;

import gift.dto.RequestDto;

public class Product {

    private Long id;
    private String name;

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

    public void update(RequestDto dto){
        this.name = dto.getName();
    }
}
