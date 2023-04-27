package cart.domain;

import lombok.Getter;

@Getter
public class Product {

    private final Integer id;
    private final String name;
    private final int price;
    private final String imageUrl;

    public Product(final String name, final int price, final String imageUrl) {
        id = null;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
