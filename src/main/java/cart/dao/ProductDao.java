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

    private static final String PRODUCT_ALL_COLUMNS = "id, name, price, image_url";
    private static final RowMapper<ProductEntity> PRODUCT_ENTITY_ROW_MAPPER = (resultSet, rowNum) -> new ProductEntity(
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

    public int insert(final ProductEntity productEntity) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(productEntity);

        return simpleJdbcInsert.executeAndReturnKey(sqlParameterSource).intValue();
    }

    public Optional<ProductEntity> findById(final long id) {
        String sql = "select " + PRODUCT_ALL_COLUMNS + " from Product where id = ?";

        List<ProductEntity> result = jdbcTemplate.query(sql, PRODUCT_ENTITY_ROW_MAPPER, id);

        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(result.get(0));
    }

    public List<ProductEntity> findAll() {
        String sql = "select " + PRODUCT_ALL_COLUMNS + " from Product";

        return jdbcTemplate.query(sql, PRODUCT_ENTITY_ROW_MAPPER);
    }

    public void update(final int id, final ProductEntity productEntity) {
        String sql = "update Product set name = ?, price = ?, image_url = ? where id = ?";

        jdbcTemplate.update(sql,
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getImageUrl(),
                id
        );
    }

    public void delete(final int id) {
        String sql = "delete from product where id = ?";

        jdbcTemplate.update(sql, id);
    }
}
