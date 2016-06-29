package commerce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author chanwook
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberNumber;

    @Column(nullable = false, unique = true, updatable = false, length = 20)
    private String memberId;

    public Member(String memberId) {
        this.memberId = memberId;
    }
}
