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

    // 상품 목록 페이지git
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", products.values());
        return "admin/list";
    }

    // 상품 등록 폼
    @GetMapping("/add")
    public String CreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("formType", "add");
        return "admin/form";
    }

    // 상품 등록 처리 -> 상품 등록에서  method="post" 로 등록 해놓았기 때문에 이곳으로 보내짐
    // 받은 후에 DB에 저장해줌
    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        long id = idGenerator.getAndIncrement();
        product.setId(id);
        products.put(id, product);
        // update된 사항을 반영하기 위해 "redirect"
        return "redirect:/admin/products";
    }

    // 상품 수정 폼
    @GetMapping("/{id}/edit")
    public String EditProduct(@PathVariable Long id, Model model) {
        Product product = products.get(id);
        model.addAttribute("product", product);
        model.addAttribute("formType", "edit");
        return "admin/form";
    }

    // 상품 수정 처리
    @PostMapping("/{id}")
    public String UpdateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        products.put(id, product);
        return "redirect:/admin/products";
    }

    // 상품 삭제 처리
    @PostMapping("/{id}/delete")
    public String DeleteProduct(@PathVariable Long id) {
        products.remove(id);
        return "redirect:/admin/products";
    }

}
