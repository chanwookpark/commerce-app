package commerce.repository;

import commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chanwook
 */
public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}
