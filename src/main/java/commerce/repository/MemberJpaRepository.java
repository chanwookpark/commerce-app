package commerce.repository;

import commerce.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 * @author chanwook
 */
public interface MemberJpaRepository extends JpaRepository<Member, Long>, QueryDslPredicateExecutor<Member> {

    List<Member> findByMemberName(String memberName);

//    List<Member> findByMemberNumberLike(String memberNumber);

    List<Member> findByMemberTypeAndMemberStatus(Member.MemberType type, Member.MemberStatus status);

}
