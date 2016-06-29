package commerce.repository;

import commerce.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chanwook
 */
public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
