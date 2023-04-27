package cart.entity;

import lombok.Getter;

@Getter
public class ProductEntity {

    private final Integer id;
    private final String name;
    private final int price;
    private final String imageUrl;

    public ProductEntity(final int id, final String name, final int price, final String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductEntity(final String name, final int price, final String imageUrl) {
        id = null;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
