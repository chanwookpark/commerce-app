package commerce.integration;

import commerce.CommerceApp;
import commerce.MemberTestSupport;
import commerce.dto.MemberSignUpResponse;
import commerce.entity.Address;
import commerce.entity.Corporation;
import commerce.entity.Member;
import commerce.repository.CorporationJpaRepository;
import commerce.repository.MemberJpaRepository;
import commerce.service.MemberSignUpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = CommerceApp.class)
public class MemberIntegrationTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    MemberJpaRepository mr;

    @Autowired
    CorporationJpaRepository cr;

    @Autowired
    MemberSignUpService ms;

    @Test
    public void simpleInsertAndSelect() throws Exception {
        //TODO primitive type일 때 안된다..
        final Member inserted = em.persist(MemberTestSupport.getMember("ironman"));
//        final Member inserted = mr.save(new Member("ironman"));

        assertThat(inserted).isNotNull();
        assertThat(mr.getOne(inserted.getMemberNumber())).isNotNull();
    }

    @Test
    public void 회원가입_개인회원() throws Exception {
        final Member newMember = MemberTestSupport.getMember("tor");

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

    @Test
    public void 회원가입_기업회원() throws Exception {
        final Member newMember = MemberTestSupport.getMember("ironman");

        // 기업회원은 소속회사를 지정하지 않으면 에러 발생!!!
        newMember.setAffiliated(null);

        MemberSignUpResponse response = ms.signUp(newMember);
        assertThat(response.isResult()).isFalse();

        // 소속회사 설정
        final Corporation corporation = new Corporation("어벤저스");
        cr.save(corporation);// 소속사 생성 (cascade 설정 안하는게 맞아)
        newMember.setAffiliated(corporation);

        response = ms.signUp(newMember);
        assertThat(response.isResult()).isTrue();
        assertThat(response.getMember().getMemberNumber()).isGreaterThan(0);
        assertThat(response.getMember().getMemberStatus()).isEqualTo(Member.MemberStatus.H);

        //DB 확인
//        final Member persisted = em.find(Member.class, response.getMember().getMemberNumber());
        final Member persisted = mr.findOne(response.getMember().getMemberNumber());
        assertThat(persisted).isNotNull();
        assertThat(persisted.getAffiliated().getCorporationId()).isEqualTo(corporation.getCorporationId());
    }

    @Test
    public void 주소추가() throws Exception {
        // 주소는 회원 엔티티를 통해서만 추가할 수 있도록..
        final Member newMember = MemberTestSupport.getMember("tor");
        // 주소 추가. 별도의 Address 용 Repository 없음
        final Address address = new Address(false, "123456", "서울시 잘살구 부자동", "고층아파트 102동 8801호");
        newMember.addAddress(address);

        mr.save(newMember);

        final Address persisted = em.find(Address.class, address.getAddressId());
        assertThat(persisted).isNotNull();
        assertThat(persisted.isDefaultAddress()).isFalse();
        assertThat(persisted.getZipCode()).isEqualTo(address.getZipCode());
        assertThat(persisted.getAddressFirst()).isEqualTo(address.getAddressFirst());
        assertThat(persisted.getAddressSecond()).isEqualTo(address.getAddressSecond());
    }
}
