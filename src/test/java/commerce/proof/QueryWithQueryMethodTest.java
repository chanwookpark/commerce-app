package commerce.proof;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import commerce.CommerceApp;
import commerce.entity.Member;
import commerce.repository.MemberJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static commerce.entity.Member.MemberStatus;
import static commerce.entity.Member.MemberType;
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
public class QueryWithQueryMethodTest {

    @Autowired
    MemberJpaRepository mr;

    @Test
    public void 기본조회() throws Exception {
        // =
        List<Member> list = mr.findByMemberName("아이언맨");
        assertThat(list.size()).isEqualTo(1);

        // like - memberNumber는 long 타입이라 쿼리 메서드로는 like 검색이 안됨
//        list = mr.findByMemberNumberLike("1%");
//        assertThat(list.size()).isEqualTo(3);

        // and
        list = mr.findByMemberTypeAndMemberStatus(MemberType.P, MemberStatus.A);
        assertThat(list.size()).isEqualTo(2);
    }
}
