package cart.controller;

import cart.dto.ProductDto;
import cart.dto.ProductRequest;
import cart.entity.ProductEntity;
import cart.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    @GetMapping
    @ApiOperation(value = "관리자 도구 페이지")
    public String adminPage(final Model model) {
        final List<ProductEntity> products = productService.findAll();

        model.addAttribute("products", products);

        return "admin";
    }

    @ApiOperation(value = "상품 추가")
    @PostMapping("/product")
    public ResponseEntity<Void> createProduct(@RequestBody @Valid final ProductRequest productRequest) {
        ProductDto productDto = ProductDto.from(productRequest);

        Long id = productService.register(productDto);

        return ResponseEntity.created(URI.create("/admin/product/" + id)).build();
    }

    @ApiOperation(value = "상품 수정")
    @PutMapping("/product/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateProduct(@PathVariable final long productId, final @RequestBody @Valid ProductRequest productRequest) {
        productService.updateProduct(productId, ProductDto.from(productRequest));
    }

    @ApiOperation(value = "상품 삭제")
    @DeleteMapping("/product/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteProduct(@PathVariable("productId") final long productId) {
        productService.deleteProduct(productId);
    }
}
