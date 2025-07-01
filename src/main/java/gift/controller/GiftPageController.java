package gift.controller;

import gift.dto.GiftRequestDto;
import gift.dto.GiftResponseDto;
import gift.dto.GiftUpdateDto;
import gift.service.GiftService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gift-page")
public class GiftPageController {

  private final GiftService giftService;

  public GiftPageController(GiftService giftService) {
    this.giftService = giftService;
  }

  @GetMapping
  public String showList(Model model) {
    model.addAttribute("gifts", giftService.findAll());
    return "gift/list";
  }

  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("gift", new GiftRequestDto());
    model.addAttribute("formAction", "/gift-page");
    return "gift/form";
  }

  @PostMapping
  public String createGift(@ModelAttribute GiftRequestDto dto) {
    giftService.saveGift(dto);
    return "redirect:/gift-page";
  }

  @GetMapping("/{id}")
  public String showEditForm(@PathVariable Long id, Model model) {
    GiftResponseDto giftToEdit = giftService.findById(id);
    GiftUpdateDto updateDto = new GiftUpdateDto(
        giftToEdit.name(),
        giftToEdit.price(),
        giftToEdit.imageUrl()

    );

    model.addAttribute("gift", updateDto);
    model.addAttribute("formAction", "/gift-page/" + id + "/edit");

    return "gift/form";
  }

  @PostMapping("/{id}/edit")
  public String updateGift(@PathVariable Long id, @ModelAttribute GiftUpdateDto dto) {
    giftService.update(id, dto);
    return "redirect:/gift-page";
  }

  @PostMapping("/{id}/delete")
  public String deleteGift(@PathVariable Long id) {
    giftService.deleteGift(id);
    return "redirect:/gift-page";
  }
}
