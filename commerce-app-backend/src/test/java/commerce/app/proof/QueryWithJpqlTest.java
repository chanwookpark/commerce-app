package commerce.app.proof;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import commerce.app.TestConfig;
import commerce.app.entity.Sku;
import commerce.app.repository.SkuJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
public class QueryWithJpqlTest {

    @Autowired
    SkuJpaRepository sr;

    @Test
    public void 기본조회() throws Exception {
        List<Sku> list = sr.findByStockedProduct("3001");
        assertThat(list.size()).isEqualTo(2);

        list = sr.findByDisplayNameLike("X라지");
        assertThat(list.size()).isEqualTo(3);
    }
}
