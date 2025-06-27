package gift.controller;

import gift.dto.ProductRequestDto;
import gift.entity.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/admin") //prefix설정
@Controller//Controller는 mvc에서 화면을 구성하기 위해서 뷰 이름을 반환하고 ViewResolver를 거치게 됩니다.
public class AdminController {

    private final Map<Long, Product> products = new HashMap<>();
    private static Long pid = 0L;

    private JdbcTemplate jdbcTemplate;

    //의존성 주입(생성자가 1개인 경우 @Autowired 생략 가능)
    public AdminController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public String home() {
        return "redirect:/admin/products/list";
    }

    //create
    //생성한 product는 HashMap에 저장
    @PostMapping("/products/add")
    public String createProduct(@ModelAttribute ProductRequestDto requestDto) {
        if (checkProduct(requestDto)) {
            String sql = "INSERT INTO PRODUCTS (NAME, PRICE, IMAGEURL) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, requestDto.getName(), requestDto.getPrice(),
                    requestDto.getImageUrl());
            return "redirect:/admin/products/list"; //GetMapping 되어 있는 것을 호출,,,?
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "가격은 음수가 될 수 없으며, 상품명, 가격, 이미지 주소는 필수 값입니다.");
    }

    //form.html을 불러오기 위한 메서드
    @GetMapping("/products/add")
    public String productForm() {
        return "form";
    }

    //read
    //특정 상품을 조회(id)
    @GetMapping("/products/info")
    public String getProduct(
            @RequestParam Long id,
            Model model
    ) {
        Optional<Product> product = findProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "productinfo";
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 상품입니다.");
    }

    //read
    //전체 상품을 조회
    @GetMapping("/products/list")
    public String getProducts(Model model) {
        String sql = "select id, name, price, imageurl from products";
        List<Product> productList = jdbcTemplate.query(sql, productRowMapper());
        model.addAttribute("productList", productList);
        return "home";
    }

    //modify.html을 불러오기 위한 메서드
    @GetMapping("/products/modify/{id}")
    public String modifyForm(
            @PathVariable Long id,
            Model model
    ) {
        Product product = products.get(id);
        model.addAttribute("product", product);
        return "modify";

    }

    //update
    //상품 수정
    @PutMapping("/products/modify/{id}")
    public String modifyProduct(
            @ModelAttribute ProductRequestDto requestDto,
            @PathVariable Long id
    ) {
        Product product = new Product(id,
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl());
        products.put(id, product);
        return "redirect:/admin/products/list";
    }

    //delete
    //등록된 상품을 삭제
    @PostMapping("/products/remove/{id}")
    public String removeProduct(@PathVariable Long id) {
        Long productId = findProductById(id).get().getId();
        String sql = "delete from products where id = ?";
        jdbcTemplate.update(sql, productId);
        return "redirect:/admin/products/list";
    }

    public boolean checkProduct(ProductRequestDto requestDto) {
        //상품명, 가격, 이미지의 경우 모두 필수 값
        if (requestDto.getName() == null || requestDto.getPrice() == null
                || requestDto.getImageUrl() == null) {
            return false;
        }
        //가격은 0이하가 될 수 없음
        else if (requestDto.getPrice() < 0) {
            return false;
        }
        return true;
    }

    public Optional<Product> findProductById(Long id) {
        String sql = "select * from products where id = ?";
        List<Product> productList = jdbcTemplate.query(sql, productRowMapper(), id);
        return productList.stream().findAny();
    }

    private RowMapper<Product> productRowMapper() {
        return new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Integer price = rs.getInt("price");
                String imageUrl = rs.getString("imageurl");
                return new Product(id, name, price, imageUrl);
            }
        };
    }
}

