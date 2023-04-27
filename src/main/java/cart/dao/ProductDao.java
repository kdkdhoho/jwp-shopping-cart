package cart.dao;

import cart.entity.ProductEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDao {

    private static final RowMapper<ProductEntity> productRowMapper = (resultSet, rowNum) -> new ProductEntity(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("image_url")
    );

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
    }

    public long insert(final ProductEntity productEntity) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(productEntity);

        return simpleJdbcInsert.executeAndReturnKey(sqlParameterSource).longValue();
    }

    public Optional<ProductEntity> findById(final long id) {
        String sql = "select id, name, price, image_url from Product where id = ?";

        List<ProductEntity> result = jdbcTemplate.query(sql, productRowMapper, id);

        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(result.get(0));
    }
}
