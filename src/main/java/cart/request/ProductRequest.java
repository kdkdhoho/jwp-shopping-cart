package cart.request;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductRequest {

    @NotNull(message = "상품의 이름을 입력해주세요.")
    @NotBlank(message = "상품의 이름을 입력해주세요.")
    private String name;

    @NotNull(message = "상품의 가격을 입력해주세요.")
    @Range(min = 0, max = 2_100_000_000, message = "{min}원 이상 {max}원 이하로만 설정 가능합니다.")
    private int price;

    @NotNull(message = "상품의 사진을 등록해주세요.")
    @URL(protocol = "https", message = "유효하지 않은 프로토콜입니다.")
    private String imageUrl;

    private ProductRequest() {}

    public ProductRequest(final String name, final int price, final String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
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
