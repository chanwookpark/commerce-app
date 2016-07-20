package commerce.repository;

import commerce.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @author chanwook
 */
public interface MemberJpaRepository extends JpaRepository<Member, Long>, QueryDslPredicateExecutor<Member> {
}
