package commerce.repository;

import commerce.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chanwook
 */
public interface SkuJpaRepository extends JpaRepository<Sku, Long> {

    @Query("SELECT s FROM commerce.entity.Sku s WHERE s.product.productId = ?1 and s.stock > 0")
    List<Sku> findByStockedProduct(String productId);

    @Query("SELECT s FROM commerce.entity.Sku s WHERE s.displayName like ?1%")
    List<Sku> findByDisplayNameLike(String displayName);
}
