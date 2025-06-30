package gift;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Product {
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

}
