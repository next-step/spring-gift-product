package gift.controller;

import gift.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
	private final Map<Long, Product> products = new HashMap<>();

	@PostConstruct
	public void init() {
		products.put(8146027L, new Product(8146027L, "아이스 카페 아메리카노 T", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
		System.out.println("initialized size: " + products.size());
	}

	@GetMapping("/products")
	public Collection<Product> getAllProducts() {
		return products.values();
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = products.get(id);

		if (product != null) {
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
