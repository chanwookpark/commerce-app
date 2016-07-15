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
public class Sku {

    @Id
    @GeneratedValue
    private long skuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    private String skuDisplayName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SKU_PRD_ATTR_VALUE",
            joinColumns = {@JoinColumn(name = "skuId")},
            inverseJoinColumns = {@JoinColumn(name = "valueId")})
    private List<ProductOptionValue> optionValueList = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "retailPrice"))
    })
    private Money retailPrice;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "salesPrice"))
    })
    private Money salesPrice;

    private long stock; //TODO 주문 가능 수량 별도 관리하도록 추가?

    public Sku(long skuId) {
        this.skuId = skuId;
    }

    public Sku(long skuId, List<ProductOptionValue> optionValueList) {
        this.skuId = skuId;
        this.optionValueList = optionValueList;
    }

    public Sku(long skuId, long stock, long salesPrice) {
        this.salesPrice = new Money(salesPrice);
        this.skuId = skuId;
        this.stock = stock;
    }

    public boolean hasQuantity(int orderQuantity) {
        return getStock() >= orderQuantity;
    }

}
