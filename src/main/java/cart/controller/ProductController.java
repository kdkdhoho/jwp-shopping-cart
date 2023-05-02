package cart.controller;

import cart.dto.ProductResponse;
import cart.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = {"/products", "/"})
    public String indexPage(final Model model) {
        final List<ProductResponse> products = productService.findAll();

        model.addAttribute("products", products);

        return "index";
    }
}
