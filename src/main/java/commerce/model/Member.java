package commerce.model;

/**
 * @author chanwook
 */
public class Member {

    private String memberId;

    public Member(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
