package gift.entity;

public class Product {
    private Long id;
    private String name;
    private Long price;

    public Product(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product (String name, Long price) {
        this(0L, name, price);
    }

    public void setId(Long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(Long price){
        this.price = price;
    }

    public Long id(){
        return id;
    }
    public String name(){
        return name;
    }
    public Long price(){
        return price;
    }
}
