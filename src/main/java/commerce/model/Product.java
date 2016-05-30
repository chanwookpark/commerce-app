package commerce.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chanwook
 */
public class Product {

    private long productId;

    private String displayName;

    private String productName;

    private Category displayCategory;

    private ProductType productType = ProductType.P;

    private Set<ProductOption> optionList = new HashSet<>();

    public Product() {
    }

    public Product(long productId, String productName, Category displayCategory) {
        this.productId = productId;
        this.productName = productName;
        this.displayCategory = displayCategory;
    }

    public void setDisplayCategory(Category displayCategory) {
        this.displayCategory = displayCategory;
    }

    public Category getDisplayCategory() {
        return displayCategory;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}

