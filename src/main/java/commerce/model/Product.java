package commerce.model;

/**
 * @author chanwook
 */
public class Product {

    private long productId;

    private String displayName;

    private String productName;

    private Category displayCategory;

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
}

