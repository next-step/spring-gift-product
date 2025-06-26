package gift.controller;

import gift.dto.ItemRequest;
import gift.dto.ItemResponse;
import gift.service.ItemService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/items")
public class AdminItemController {

    private final ItemService itemService;

    public AdminItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String listItems(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortProperty,
        @RequestParam(defaultValue = "asc") String sortDirection,
        Model model
    ) {
        List<ItemResponse> items = itemService.getAllItems(page, size, sortProperty, sortDirection);
        model.addAttribute("items", items);
        return "admin/items/list";
    }

    @GetMapping("/new")
    public String newItemForm(Model model) {
        model.addAttribute("item", new ItemRequest(null, null, 0, null));
        return "admin/items/form";
    }

    @PostMapping
    public String createItem(
        @Valid @ModelAttribute("item") ItemRequest itemRequest,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/items/form";
        }
        try {
            itemService.createItem(itemRequest);
            redirectAttributes.addFlashAttribute("message", "상품이 성공적으로 등록되었습니다!");
            return "redirect:/admin/items";
        } catch (
            ResponseStatusException ex) { // 현재 단계에서는 HTTP에 종속된다는 문제가 있음(아래 try-catch 문들도 동일). 추후 예외 처리를 고도화할 필요가 있는 코드
            redirectAttributes.addFlashAttribute("errorMessage", ex.getReason());
            return "admin/items/form";
        }
    }

    @GetMapping("/{id}")
    public String detailItem(@PathVariable("id") Long id, Model model) {
        try {
            ItemResponse item = itemService.getItemById(id);
            model.addAttribute("item", item);
        } catch (ResponseStatusException ex) {
            model.addAttribute("errorMessage", ex.getReason());
            model.addAttribute("item", null);
        }
        return "admin/items/detail";
    }

    @GetMapping("/{id}/edit")
    public String editItemForm(@PathVariable("id") Long id, Model model) {
        try {
            ItemResponse item = itemService.getItemById(id);
            model.addAttribute("item",
                new ItemRequest(item.id(), item.name(), item.price(), item.imageUrl()));
            return "admin/items/form";
        } catch (ResponseStatusException ex) {
            model.addAttribute("errorMessage", ex.getReason());
            return "redirect:/admin/items";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateItem(
        @PathVariable("id") Long id,
        @Valid @ModelAttribute("item") ItemRequest itemRequest,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/items/form";
        }
        try {
            itemService.updateItem(id, itemRequest);
            redirectAttributes.addFlashAttribute("message", "상품이 성공적으로 수정되었습니다!");
            return "redirect:/admin/items/" + id;
        } catch (ResponseStatusException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getReason());
            return "admin/items/form";
        }
    }

}
