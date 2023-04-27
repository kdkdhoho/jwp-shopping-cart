package cart.response;

import cart.entity.ProductEntity;

public class ProductResponse {

    private Integer id;
    private String name;
    private int price;
    private String imageUrl;

    private ProductResponse() {}

    public ProductResponse(final Integer id, final String name, final int price, final String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public static ProductResponse from(final ProductEntity productEntity) {
        return new ProductResponse(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getImageUrl()
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
