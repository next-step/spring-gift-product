package gift.controller;

import gift.dto.request.RequestGift;
import gift.dto.request.RequestModifyGift;
import gift.dto.response.ResponseGift;
import gift.repository.GiftJdbcRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GiftWebViewController {
    private final GiftJdbcRepository giftRepository;

    public GiftWebViewController(GiftJdbcRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @GetMapping("/admin")
    public String adminWebView(Model model) {
        model.addAttribute("gifts", giftRepository.findAll());
        return "/admin.html";
    }

    @GetMapping("/addItem")
    public String addItemWebView(){
        return "/addGift.html";
    }

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute RequestGift requestGift, RedirectAttributes redirectAttributes){
        ResponseGift responseGift = ResponseGift.from(giftRepository.save(RequestGift.toEntity(requestGift)));
        redirectAttributes.addAttribute("giftId", responseGift.id());
        return "redirect:/gift/{giftId}";
    }

    @GetMapping("/gift/{id}")
    public String giftWebView(Model model, @PathVariable Long id){
        model.addAttribute("gift", giftRepository.findById(id).orElse(null));
        return "/singleGift.html";
    }

    @GetMapping("/gift/{id}/edit")
    public String editWebView(Model model, @PathVariable Long id){
        model.addAttribute("gift", giftRepository.findById(id).orElse(null));
        return "/modify.html";
    }

    @PostMapping("gift/{id}/edit")
    public String edit(
            @PathVariable Long id,
            @ModelAttribute RequestModifyGift item,
            RedirectAttributes redirectAttributes
    ) {
        giftRepository.modify(id, RequestModifyGift.toEntity(item));
        redirectAttributes.addAttribute("giftId", id);
        return "redirect:/gift/{giftId}";
    }

    @PostMapping("/gift/{id}/delete")
    public String delete(@PathVariable Long id){
        giftRepository.deleteById(id);
        return "redirect:/admin";
    }
}
