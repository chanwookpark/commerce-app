package commerce.app.repository;

import commerce.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author chanwook
 */
public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    /**
     * @param keyword
     * @return
     */
    List<Product> findByProductNameLike(String keyword);
}
