package commerce;

import commerce.repository.MemberJpaRepository;
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

    @Test
    public void simpleInsertAndSelect() throws Exception {
        //TODO primitive type일 때 안된다..
        final Member inserted = em.persist(new Member("zzz"));
//        final Member inserted = mr.save(new Member("zzz"));

        assertThat(inserted).isNotNull();
        assertThat(mr.getOne(inserted.getMemberNumber())).isNotNull();
    }
}
