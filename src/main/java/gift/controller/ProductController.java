package gift.controller;

import gift.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ProductController {
	private final Map<Long, Product> products = new HashMap<>();

	@PostConstruct
	public void init() {
		products.put(8146027L, new Product(8146027L, "아이스 카페 아메리카노 T", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
		System.out.println("initialized: total " + products.size() + " products");
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

	// ID 충돌 시 409 conflict 예외처리 -> ID를 서버에서 생성한다면?
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		if (products.containsKey(product.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		if (!validateProduct(product)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		products.put(product.getId(), product);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		Product existingProduct = products.get(id);

		if (existingProduct == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (!Objects.equals(existingProduct.getId(), product.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (!validateProduct(product)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setImageUrl(product.getImageUrl());

		return new ResponseEntity<>(existingProduct, HttpStatus.OK);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		if (products.remove(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private boolean validateProduct(Product product) {
		if (product.getName() == null || product.getName().trim().isEmpty()) {
			return false;
		}
		if (product.getPrice() <= 0) {
			return false;
		}
		String imageUrl = product.getImageUrl();
		if (imageUrl == null || imageUrl.trim().isEmpty()) {
			return false;
		}
		String urlRegex = "^(https?|ftp)://[^\\s/$.?#].\\S*$";
		return imageUrl.matches(urlRegex);
	}
}
