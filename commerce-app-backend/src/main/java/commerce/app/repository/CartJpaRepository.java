package commerce.app.repository;

import commerce.app.entity.Cart;
import commerce.app.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chanwook
 */
public interface CartJpaRepository extends JpaRepository<Cart, Long> {

    Cart findByOwner(Member memberId);

}
