package commerce.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chanwook
 */
public class Product extends ProductBaseAttribute {

    private Set<ProductOption> optionList = new HashSet<>();

    public Product() {
    }

    public Product(long productId, String productName, Category displayCategory) {
        this.productId = productId;
        this.productName = productName;
        this.displayCategory = displayCategory;
    }

    public void addOption(ProductOption option) {
        this.optionList.add(option);
    }

    public Set<ProductOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(Set<ProductOption> optionList) {
        this.optionList = optionList;
    }

    
}

