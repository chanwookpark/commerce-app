package commerce.app.proof;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import commerce.app.CommerceApp;
import commerce.app.entity.ProductOption;
import commerce.app.repository.ProductOptionJpaRepository;
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
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommerceApp.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
        listeners = {DbUnitTestExecutionListener.class})
@DatabaseSetup("sample-data.xml")
public class EqualsAndHashcodeByPrimaryKeyTest {

    TestEntityManager em1;

    TestEntityManager em2;

    @Autowired
    @Qualifier("entityManagerFactory")
    EntityManagerFactory emf1;

    @Autowired
    @Qualifier("entityManagerFactoryMore")
    EntityManagerFactory emf2;

    @Autowired
    ProductOptionJpaRepository por;

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

        final ProductOption po1 = por.findOne(2001L);
        final ProductOption po2 = por.findOne(2001L);

        assertThat(po1.equals(po2)).isTrue();
    }

    @Test
    public void 다른세션에조회한엔티티비교() throws Exception {

        final ProductOption po1 = em1.find(ProductOption.class, 2001L);
        final ProductOption po2 = em2.find(ProductOption.class, 2001L);

        assertThat(po1.equals(po2)).isTrue();
    }

    @Test
    public void SET에직접저장() throws Exception {
        Set<ProductOption> set = new HashSet<>();
        final long optionId = 2001L;

        // 조회해서 SET에 저장하고..
        final ProductOption po1 = por.findOne(optionId);
        set.add(po1);

        assertThat(set.size()).isEqualTo(1);

        // 세션에서 다시 조회해도 동일한 객체니 SET은 사이즈가 1
        final ProductOption po2 = por.findOne(optionId);
        assertThat(po1.equals(po2)).isTrue();
        set.add(po2);
        assertThat(set.size()).isEqualTo(1);
    }

}
