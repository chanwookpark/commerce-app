package commerce.repository;

import commerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chanwook
 */
public interface CartJpaRepository extends JpaRepository<Cart, Long> {
}
