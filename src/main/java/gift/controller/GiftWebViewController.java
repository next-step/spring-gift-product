package gift.controller;

import gift.dto.request.RequestGift;
import gift.dto.request.RequestModifyGift;
import gift.dto.response.ResponseGift;
import gift.entity.Gift;
import gift.repository.GiftRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GiftWebViewController {
    private final GiftRepository giftRepository;

    public GiftWebViewController(GiftRepository giftRepository) {
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
        ResponseGift responseGift = giftRepository.save(requestGift);
        redirectAttributes.addAttribute("giftId", responseGift.id());
        return "redirect:/gift/{giftId}";
    }

    @GetMapping("/gift/{id}")
    public String giftWebView(Model model, @PathVariable Long id){
        model.addAttribute("gift", giftRepository.findById(id));
        return "/singleGift.html";
    }

    @GetMapping("/gift/{id}/edit")
    public String editWebView(Model model, @PathVariable Long id){
        model.addAttribute("gift", giftRepository.findById(id));
        return "/modify.html";
    }

    @PostMapping("/{id}/edit")
    public String edit(
            @PathVariable Long id,
            @ModelAttribute RequestModifyGift item,
            RedirectAttributes redirectAttributes
    ) {
        giftRepository.modify(id, item);
        redirectAttributes.addAttribute("giftId", id);
        return "redirect:/gift/{giftId}";
    }

    @PostMapping("/gift/{id}/delete")
    public String delete(@PathVariable Long id){
        giftRepository.delete(id);
        return "redirect:/admin";
    }
}
