package gift.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(){
        return "adminpage";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable UUID id, Model model){

        model.addAttribute("productId", id);

        return "edit";
    }
}
