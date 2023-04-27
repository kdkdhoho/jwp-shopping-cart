package cart.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

import cart.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class ProductDaoTest {

    private ProductDao productDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void beforeEach() {
        this.productDao = new ProductDao(jdbcTemplate);
    }

    @Test
    void 상품_추가_성공() {
        Product product = new Product("상품", 1000, "image");

        assertThatNoException().isThrownBy(
                () -> productDao.insert(product)
        );
    }
}
