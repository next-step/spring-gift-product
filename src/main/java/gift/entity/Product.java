package gift.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Product {
    Long id;
    String name;
    int price;
    String imageUrl;
}
