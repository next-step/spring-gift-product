package gift.admin;

import gift.Entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public AdminController() {
        // 샘플 데이터 등록
        Long id = idGenerator.getAndIncrement();
        products.put(id, new Product(id, "아이스 카페 아메리카노 T", 4500,
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
    }

    // 상품 목록 페이지
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", products.values());
        return "admin/list";
    }

}
