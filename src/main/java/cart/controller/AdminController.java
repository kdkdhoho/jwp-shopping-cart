package cart.controller;

import cart.request.ProductRequest;
import cart.response.ProductResponse;
import cart.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    public AdminController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String adminPage(final Model model) {
        List<ProductResponse> products = productService.findAll();

        model.addAttribute("products", products);

        return "admin";
    }

    @PostMapping("/product")
    public ResponseEntity<Void> enroll(final @RequestBody @Valid ProductRequest productRequest) {
        int id = productService.enroll(productRequest);

        return ResponseEntity.created(URI.create("/admin/product/" + id)).build();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> findById(final @PathVariable int id) {
        Optional<ProductResponse> result = productService.findById(id);

        return result.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.of(result)
        );
    }

    @PutMapping("product/{id}")
    public ResponseEntity<Void> update(final @PathVariable int id, final @RequestBody @Valid ProductRequest productRequest) {
        productService.update(id, productRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> delete(final @PathVariable int id) {
        productService.delete(id);

        return ResponseEntity.ok().build();
    }
}
