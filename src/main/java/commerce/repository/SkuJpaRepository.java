package commerce.repository;

import commerce.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chanwook
 */
public interface SkuJpaRepository extends JpaRepository<Sku, Long> {
}
