package commerce;

import commerce.entity.Member;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chanwook
 */
public class MemberTestSupport {

    public static final String MEMBER_TOR = "tor";
    public static final String MEMBER_IRONMAN = "ironman";

    private static final Map<String, Member> TEST_MEMBER = new HashMap() {{
        put(MEMBER_TOR, new Member("tor", "tor00", "토르", Member.MemberType.P, "tor@mail.com"));
        put(MEMBER_IRONMAN, new Member("ironman", "ironman00", "아이언맨", Member.MemberType.E, "ironman@mail.com"));
    }};

    public static Member getMember(String memberId) {
        return TEST_MEMBER.get(memberId);
    }
}
