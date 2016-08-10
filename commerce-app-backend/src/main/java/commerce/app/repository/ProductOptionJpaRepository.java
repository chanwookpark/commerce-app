package commerce.app.repository;

import commerce.app.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chanwook
 */
public interface ProductOptionJpaRepository extends JpaRepository<ProductOption, Long> {
}
