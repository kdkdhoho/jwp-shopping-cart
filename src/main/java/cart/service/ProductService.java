package cart.service;

import static java.util.stream.Collectors.toList;

import cart.dao.ProductDao;
import cart.entity.ProductEntity;
import cart.request.ProductRequest;
import cart.response.ProductResponse;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(final ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<ProductResponse> findAll() {
        List<ProductEntity> result = productDao.findAll();

        return result.stream()
                .map(productEntity -> ProductResponse.from(productEntity))
                .collect(toList());
    }

    public int enroll(final ProductRequest productRequest) {
        ProductEntity productEntity = ProductEntity.from(productRequest);

        return productDao.insert(productEntity);
    }

    public Optional<ProductResponse> findById(final int id) {
        Optional<ProductEntity> result = productDao.findById(id);

        if (result.isPresent()) {
            return Optional.of(ProductResponse.from(result.get()));
        }
        return Optional.empty();
    }

    public void update(final int id, final ProductRequest productRequest) {
        ProductEntity productEntity = ProductEntity.from(productRequest);

        productDao.update(id, productEntity);
    }

    public void delete(final int id) {
        productDao.delete(id);
    }
}
