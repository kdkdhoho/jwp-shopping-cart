package cart.controller;

import cart.dto.AuthInfo;
import cart.dto.ProductResponse;
import cart.service.CartService;
import cart.util.BasicAuthorizationExtractor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String myCart() {
        return "cart";
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProductsOfCart(@RequestHeader(value = "Authorization") String credentials) {
        final AuthInfo authInfo = BasicAuthorizationExtractor.extract(credentials, "/cart");

        final List<ProductResponse> products = cartService.showProductsFrom(authInfo);

        return ResponseEntity.ok(products);
    }
}
