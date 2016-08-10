package commerce.app.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Corporation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long corporationId;

    private String corporationName;

    @OneToMany(mappedBy = "affiliated")
    private List<Member> memberList = new ArrayList<>();

    public Corporation(String corporationName) {
        this.corporationName = corporationName;
    }

}
