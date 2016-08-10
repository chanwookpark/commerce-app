package commerce.app.repository;

import commerce.app.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chanwook
 */
public interface SkuJpaRepository extends JpaRepository<Sku, Long> {

    @Query("SELECT s FROM commerce.app.entity.Sku s WHERE s.product.productId = ?1 AND s.stock > 0")
    List<Sku> findByStockedProduct(String productId);

    @Query("SELECT s FROM commerce.app.entity.Sku s WHERE s.displayName LIKE ?1%")
    List<Sku> findByDisplayNameLike(String displayName);

    @Query(value = "SELECT * FROM SKU WHERE PRODUCT_ID = ?1 AND STOCK > 0", nativeQuery = true)
    List<Sku> findByStockedProductWithNativeSQL(String productId);

    @Query(value = "SELECT * FROM SKU WHERE DISPLAY_NAME LIKE ?1%", nativeQuery = true)
    List<Sku> findByDisplayNameLikeWithNativeSQL(String displayName);
}
