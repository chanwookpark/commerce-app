package commerce;

import commerce.dto.MemberSignUpResponse;
import commerce.entity.Corporation;
import commerce.entity.Member;
import commerce.repository.MemberJpaRepository;
import commerce.service.MemberSignUpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class MemberIntegrationTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    MemberJpaRepository mr;

    @Autowired
    MemberSignUpService ms;

    @Test
    public void simpleInsertAndSelect() throws Exception {
        //TODO primitive type일 때 안된다..
        final Member inserted = em.persist(new Member("ironman"));
//        final Member inserted = mr.save(new Member("ironman"));

        assertThat(inserted).isNotNull();
        assertThat(mr.getOne(inserted.getMemberNumber())).isNotNull();
    }

    @Test
    public void 회원가입_개인회원() throws Exception {
        final Member newMember = new Member();
        newMember.setMemberId("tor");
        newMember.setPassword("1234");
        newMember.setMemberName("tor");
        newMember.setMemberType(Member.MemberType.P);

        // 개인회원은 소속회사를 지정하면 에러 발생. 일부러 한번 틀리고 다시~
        newMember.setAffiliated(new Corporation());

        MemberSignUpResponse signUpResponse = ms.signUp(newMember);
        assertThat(signUpResponse.isResult()).isFalse();

        // 소속회사 제거
        newMember.setAffiliated(null);

        signUpResponse = ms.signUp(newMember);
        assertThat(signUpResponse.isResult()).isTrue();
        assertThat(signUpResponse.getMember().getMemberNumber()).isGreaterThan(0);//회원번호가 발급됐는지 확인
        assertThat(signUpResponse.getMember().getMemberStatus()).isEqualTo(Member.MemberStatus.H);

        //DB 확인
        final Member persistedMember = em.find(Member.class, signUpResponse.getMember().getMemberNumber());
        assertThat(persistedMember).isNotNull();
    }
}
