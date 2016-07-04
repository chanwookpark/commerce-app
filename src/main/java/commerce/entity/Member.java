package commerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(optional = true)
    private Corporation affiliated;

    private MemberStatus memberStatus;

    public Member(String memberId) {
        this.memberId = memberId;
    }

    public Member(String memberId, String password, String memberName, MemberType memberType) {
        this.memberId = memberId;
        this.password = password;
        this.memberName = memberName;
        this.memberType = memberType;
    }

    public void addAddress(Address address) {
        address.setOwner(this);
        this.addressList.add(address);
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
