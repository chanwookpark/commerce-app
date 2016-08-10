package commerce.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author chanwook
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    @Column(nullable = false)
    private boolean defaultAddress;

    // 123456 형식으로 저장
    @Column(length = 6)
    private String zipCode;

    @Column(length = 50)
    private String addressFirst;

    @Column(length = 100)
    private String addressSecond;

    @Column(length = 50)
    private String testField111;

    @ManyToOne(optional = false)
    private Member owner;

    public Address(boolean defaultAddress, String zipCode, String addressFirst, String addressSecond) {
        this.defaultAddress = defaultAddress;
        this.zipCode = zipCode;
        this.addressFirst = addressFirst;
        this.addressSecond = addressSecond;
    }


}
