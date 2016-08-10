package commerce.app.proof;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.querydsl.core.types.dsl.BooleanExpression;
import commerce.app.TestConfig;
import commerce.app.entity.Member;
import commerce.app.entity.Member.MemberStatus;
import commerce.app.entity.Member.MemberType;
import commerce.app.entity.QMember;
import commerce.app.repository.MemberJpaRepository;
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
public class QueryWithQueryDslTest {

    @Autowired
    MemberJpaRepository mr;

    @Test
    public void 기본조회() throws Exception {
        final QMember member = QMember.member;

        // =
        final BooleanExpression eq = member.memberName.eq("아이언맨");
        List<Member> list = (List<Member>) mr.findAll(eq);
        assertThat(list.size()).isEqualTo(1);

        // like
        final BooleanExpression like = member.memberNumber.like("1%");
        list = (List<Member>) mr.findAll(like);
        assertThat(list.size()).isEqualTo(3);

        // and
        final BooleanExpression and = member.memberType.eq(MemberType.P)
                .and(member.memberStatus.eq(MemberStatus.A));
        list = (List<Member>) mr.findAll(and);
        assertThat(list.size()).isEqualTo(2);
    }
}
