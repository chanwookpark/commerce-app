package commerce.repository;

import commerce.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chanwook
 */
public interface ProductOptionJpaRepository extends JpaRepository<ProductOption, Long> {
}
