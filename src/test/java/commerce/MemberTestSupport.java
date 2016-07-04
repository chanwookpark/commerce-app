package commerce;

import commerce.entity.Member;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chanwook
 */
public class MemberTestSupport {

    private static final Map<String, Member> TEST_MEMBER = new HashMap() {{
        put("tor", new Member("tor", "tor00", "토르", Member.MemberType.P));
        put("ironman", new Member("ironman", "ironman00", "아이언맨", Member.MemberType.E));
    }};

    public static Member getMember(String memberId) {
        return TEST_MEMBER.get(memberId);
    }
}
