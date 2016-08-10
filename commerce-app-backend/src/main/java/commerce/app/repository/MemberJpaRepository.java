package commerce.app.repository;

import commerce.app.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 * @author chanwook
 */
public interface MemberJpaRepository extends JpaRepository<Member, Long>, QueryDslPredicateExecutor<Member> {

    Member findByMemberName(String memberName);

    Member findByMemberId(String memberId);

//    List<Member> findByMemberNumberLike(String memberNumber);

    List<Member> findByMemberTypeAndMemberStatus(Member.MemberType type, Member.MemberStatus status);

}
