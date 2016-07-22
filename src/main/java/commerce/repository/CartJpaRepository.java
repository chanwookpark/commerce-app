package commerce.repository;

import commerce.entity.Cart;
import commerce.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chanwook
 */
public interface CartJpaRepository extends JpaRepository<Cart, Long> {

    Cart findByOwner(Member memberId);

}
