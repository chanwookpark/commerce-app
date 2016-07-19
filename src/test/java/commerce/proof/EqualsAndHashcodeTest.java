package commerce.proof;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import commerce.CommerceApp;
import commerce.entity.Member;
import commerce.repository.MemberJpaRepository;
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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = CommerceApp.class)
@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
        listeners = {DbUnitTestExecutionListener.class})
@DatabaseSetup("equals-hashcode-sample-data.xml")
public class EqualsAndHashcodeTest {

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
    public void 비즈니스키로구현_동일세션에서조회한엔티티비교() throws Exception {
//        final Member newMember = MemberTestSupport.getMember("tor");
//        mr.saveAndFlush(newMember);

        final Member m1 = mr.findOne(1001l);
        final Member m2 = mr.findOne(1001l);

        assertThat(m1.equals(m2)).isTrue();
    }

    @Test
    public void 비즈니스키로구현_다른세션에조회한엔티티비교() throws Exception {

        final Member m1 = em1.find(Member.class, 1002l);
        final Member m2 = em2.find(Member.class, 1002l);

        assertThat(m1.equals(m2)).isTrue();
    }
}
