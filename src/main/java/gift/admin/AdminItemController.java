package gift.admin;

import gift.item.dto.ItemCreateDto;
import gift.item.dto.ItemResponseDto;
import gift.item.dto.ItemUpdateDto;
import gift.item.service.ItemService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminItemController {

    private final ItemService itemService;

    public AdminItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 페이지 뷰 반환

    @GetMapping("/admin/items")
    public String adminItemPage(Model model) {
        List<ItemResponseDto> items = itemService.findAll();
        model.addAttribute("items", items);
        return "admin-item";
    }

    @GetMapping("/admin/items/new")
    public String newItemPage(Model model) {
        // dto가 record 형태라 생성시 값이 필요함.
        // 임의의 데이터를 넣어도 클라이언트에서 제출 시 새로운 객체가 생성됨.
        model.addAttribute("item", new ItemCreateDto("", 0, ""));
        return "admin-item-new";
    }

    @GetMapping("/admin/items/update/{id}")
    public String updateItemPage(@PathVariable("id") Long id, Model model) {
        ItemResponseDto item = itemService.findItem(id);
        ItemUpdateDto updateDto = new ItemUpdateDto(item.name(), item.price(), item.imageUrl());
        model.addAttribute("item", updateDto);
        model.addAttribute("id", id);
        return "admin-item-update";
    }

    // 상품 요청 처리

    @PostMapping("/admin/items")
    public String newItem(@ModelAttribute ItemCreateDto item) {
        itemService.createItem(item);
        return "redirect:/admin/items";
    }

    @PostMapping("/admin/items/{id}")
    public String updateItem(
        @PathVariable("id") Long id,
        @ModelAttribute ItemUpdateDto item
    ) {
        itemService.updateItem(id, item);
        return "redirect:/admin/items";
    }

    @PostMapping("/admin/items/{id}/delete")
    public String deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return "redirect:/admin/items";
    }

}
