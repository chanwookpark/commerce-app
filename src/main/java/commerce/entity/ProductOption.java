package commerce.entity;

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
//@AllArgsConstructor
public class ProductOption {

    @Id
    @GeneratedValue
    private long optionId;

    private String optionName;

    private String displayName;

    @ManyToMany(mappedBy = "optionList")
    private List<Product> productList = new ArrayList<Product>();

    @OneToMany(mappedBy = "targetOption")
    private List<ProductOptionValue> optionValueList = new ArrayList<>();

    public ProductOption(long id, String name, String displayName) {
        this.optionId = id;
        this.optionName = name;
        this.displayName = displayName;
    }

    public void addProductOptionValue(ProductOptionValue value) {
        this.optionValueList.add(value);
        if (value.getTargetOption() == null || value.getTargetOption().getOptionId() != this.getOptionId()) {
            value.setTargetOption(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductOption)) return false;

        ProductOption option = (ProductOption) o;

        return new EqualsBuilder().append(optionId, option.getOptionId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(optionId).toHashCode();
    }
}
