package cart.entity;

import cart.request.ProductRequest;

public class ProductEntity {

    private Integer id;
    private String name;
    private int price;
    private String imageUrl;

    private ProductEntity() {}

    public ProductEntity(final Integer id, final String name, final int price, final String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductEntity(final String name, final int price, final String imageUrl) {
        this(null, name, price, imageUrl);
    }

    public static ProductEntity from(final ProductRequest productRequest) {
        return new ProductEntity(
                productRequest.getName(),
                productRequest.getPrice(),
                productRequest.getImageUrl()
        );
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
