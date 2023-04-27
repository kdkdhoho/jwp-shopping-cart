package cart.controller;

import cart.dao.ProductDao;
import cart.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductDao productDao;

    @GetMapping("/")
    public String indexPage(final Model model) {
        List<ProductEntity> products = productDao.findAll();

        model.addAttribute("products", products);

        return "index";
    }

    @GetMapping("/admin")
    public String adminPage(final Model model) {
        List<ProductEntity> products = productDao.findAll();

        model.addAttribute("products", products);

        return "admin";
    }
}
