package commerce.app.proof;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import commerce.app.TestConfig;
import commerce.app.entity.Cart;
import commerce.app.entity.Member;
import commerce.app.repository.CartJpaRepository;
import commerce.app.repository.MemberJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

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
public class AssocationMappingTest {

    @Autowired
    CartJpaRepository cr;

    @Autowired
    MemberJpaRepository mr;

    @Test
    public void 일대일_장바구니와회원() throws Exception {

        final Member m = mr.findByMemberId("tor");

        cr.saveAndFlush(Cart.createCart(m));

        Cart persisted = cr.findByOwner(m);
        assertThat(persisted);

        final Member memberWithCart = mr.findOne(m.getMemberNumber());
        assertThat(memberWithCart.getCart()).isNotNull();

    }
}
