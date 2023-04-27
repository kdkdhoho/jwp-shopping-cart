package cart.controller;

import cart.dao.ProductDao;
import cart.entity.ProductEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ProductController {

    private final ProductDao productDao;

    public ProductController(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/")
    public String indexPage(final Model model) {
        List<ProductEntity> products = productDao.findAll();

        model.addAttribute("products", products);

        return "index";
    }
}
