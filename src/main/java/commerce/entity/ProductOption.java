package commerce.entity;

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

    public ProductOption(int id, String name, String displayName) {
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
}
