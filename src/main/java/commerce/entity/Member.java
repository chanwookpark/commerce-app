package commerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chanwook
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberNumber;

    @Column(nullable = false, unique = true, updatable = false, length = 20)
    private String memberId;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 30)
    private String memberName;

    @OneToMany(mappedBy = "owner", orphanRemoval = true, targetEntity = Address.class, cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<>();

    @Column(nullable = false)
    private MemberType memberType;

    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @ManyToOne(optional = true)
    private Corporation affiliated;

    private MemberStatus memberStatus;

    public Member(String memberId) {
        this.memberId = memberId;
    }

    public Member(String memberId, String password, String memberName, MemberType memberType, String email) {
        this.memberId = memberId;
        this.password = password;
        this.memberName = memberName;
        this.memberType = memberType;
        this.email = email;
    }

    public void addAddress(Address address) {
        address.setOwner(this);
        this.addressList.add(address);
    }

    /**
     * 비즈니스 키(3번 케이스) 비교 구현
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;

        Member member = (Member) o;

        return new EqualsBuilder()
                .append(memberId, member.getMemberId())
                .append(email, member.getEmail())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(memberId).append(email).toHashCode();
    }

    //TODO enum 공통화?
    public enum MemberType {
        E("ENTERPRISE"), P("PERSONAL");

        private String description;

        MemberType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    //TODO enum 공통화?
    public enum MemberStatus {
        A("ACTIVE"), I("INACTIVE"), L("LOCKED"), H("HOLD_ON_JOIN");

        private String description;

        MemberStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
