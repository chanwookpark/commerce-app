package commerce.repository;

import commerce.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chanwook
 */
public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
