package commerce.unit;

import commerce.entity.Address;
import commerce.entity.Corporation;
import commerce.entity.Member;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author chanwook
 */
public class MemberTest {

    @Test
    public void 멤버속성추가() throws Exception {
        Member m = new Member();
        m.setMemberNumber(1234L); //자동생성
        m.setMemberId("test01");
        m.setPassword("1234");
        m.setMemberName("홍길동");
        m.setMemberType(Member.MemberType.E);
        m.addAddress(new Address());
        m.setAffiliated(new Corporation(1, "잘나간다 회사", Arrays.asList(m)));
    }
}
