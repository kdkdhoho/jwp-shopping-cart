package cart.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertAll;

import cart.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import java.util.Optional;

@JdbcTest
@Sql("classpath:schema.sql")
class ProductDaoTest {

    private ProductDao productDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void beforeEach() {
        this.productDao = new ProductDao(jdbcTemplate);

        settingDummyData();
    }

    private void settingDummyData() {
        String sql1 = "INSERT INTO Product (name, price, image_url) values ('상품1', 1000, '상품1 이미지');";
        String sql2 = "INSERT INTO Product (name, price, image_url) values ('상품2', 2000, '상품2 이미지');";

        jdbcTemplate.execute(sql1 + sql2);
    }

    @Test
    void 추가_성공() {
        ProductEntity productEntity = new ProductEntity("상품", 1000, "image");

        assertThatNoException().isThrownBy(
                () -> productDao.insert(productEntity)
        );
    }

    @Test
    void 존재하지_않는_상품_조회시_Optional에_null이_포함되어_반환된다() {
        Optional<ProductEntity> result = productDao.findById(3);

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void 조회_성공() {
        Optional<ProductEntity> result = productDao.findById(1);

        assertThat(result.isPresent()).isTrue();

        ProductEntity foundProductEntity = result.get();
        assertAll(
                () -> assertThat(foundProductEntity.getName()).isEqualTo("상품1"),
                () -> assertThat(foundProductEntity.getPrice()).isEqualTo(1000),
                () -> assertThat(foundProductEntity.getImageUrl()).isEqualTo("상품1 이미지")
        );
    }

    @Test
    void 모든_데이터_조회_성공() {
        List<ProductEntity> result = productDao.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
