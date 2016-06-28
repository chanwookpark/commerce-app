package commerce.model;

/**
 * @author chanwook
 */
public class ProductBaseAttribute {

    protected long productId;

    protected String displayName;

    protected String productName;

    protected Category displayCategory;

    protected ProductType productType = ProductType.P;

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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

}
