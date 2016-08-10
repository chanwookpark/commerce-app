package commerce.app.proof;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import commerce.app.MemberTestSupport;
import commerce.app.TestConfig;
import commerce.app.entity.Member;
import commerce.app.repository.MemberJpaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
        listeners = {DbUnitTestExecutionListener.class})
@DatabaseSetup("/sample-data.xml")
public class EqualsAndHashcodeByBusinessKeyTest {

    TestEntityManager em1;

    TestEntityManager em2;

    @Autowired
    MemberJpaRepository mr;

    @Autowired
    @Qualifier("entityManagerFactory")
    EntityManagerFactory emf1;

    @Autowired
    @Qualifier("entityManagerFactoryMore")
    EntityManagerFactory emf2;

    @Before
    public void setUp() throws Exception {
        em1 = new TestEntityManager(emf1);
        em2 = new TestEntityManager(emf2);
    }

    /**
     * 동일 세션에서는 equals/hashcode를 구현하지 않더라도 세션에서 동일한 객체임을 보장해 줌
     *
     * @throws Exception
     */
    @Test
    public void 동일세션에서조회한엔티티비교() throws Exception {

        final Member m1 = mr.findOne(1001L);
        final Member m2 = mr.findOne(1001L);

        assertThat(m1.equals(m2)).isTrue();
    }

    @Test
    public void 다른세션에조회한엔티티비교() throws Exception {

        final Member m1 = em1.find(Member.class, 1002L);
        final Member m2 = em2.find(Member.class, 1002L);

        assertThat(m1.equals(m2)).isTrue();
    }

    @Test
    public void SET에직접저장() throws Exception {
        Set<Member> set = new HashSet<>();
        final long memberNumber = 1001L;

        // 조회해서 SET에 저장하고..
        final Member m1 = mr.findOne(memberNumber);
        set.add(m1);

        assertThat(set.size()).isEqualTo(1);

        // 세션에서 다시 조회해도 동일한 객체니 SET은 사이즈가 1
        final Member m2 = mr.findOne(memberNumber);
        assertThat(m1.equals(m2)).isTrue();
        set.add(m2);
        assertThat(set.size()).isEqualTo(1);
    }

    @Test(expected = PersistenceException.class)
    public void 비즈니스키가동일한신규엔티티생성() throws Exception {
        final Member m1 = mr.findOne(1001L);

        // 새로운 멤버 인스턴스를 만들었는데 비즈니스키인 memberId와 email이 동일하면
        Member m2 = MemberTestSupport.getMember("tor"); // 1001은 tor...
        // email, memberId의 키 충돌 예외 발생. 세션에서는 m2의 식별성을 확인할 수 없으니 별다른 에러가 발생하지 않음..
        mr.saveAndFlush(m2);
    }
}
