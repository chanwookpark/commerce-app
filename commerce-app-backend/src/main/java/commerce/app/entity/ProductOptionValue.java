package commerce.app.entity;

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
//@AllArgsConstructor
public class ProductOptionValue {

    @Id
    @GeneratedValue
    private long valueId;

    @ManyToOne
    @JoinColumn(name = "TARGET_OPTION", nullable = false, updatable = false)
    private ProductOption targetOption;

    @ManyToMany(mappedBy = "optionValueList")
    private List<Sku> skuList = new ArrayList<>();

    private String value;

    private String displayName;

    public ProductOptionValue(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public ProductOptionValue(long id, ProductOption option, String value, String displayName) {
        this.valueId = id;
        this.targetOption = option;
        this.value = value;
        this.displayName = displayName;
    }
}
