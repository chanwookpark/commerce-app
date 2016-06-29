package commerce;

/**
 * @author chanwook
 */
public class ProductOptionValue {

    private long id;

    private ProductOption targetOption;

    private String value;

    private String displayName;

    public ProductOptionValue(long id, ProductOption option, String value, String displayName) {
        this.id = id;
        this.targetOption = option;
        this.value = value;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductOption getTargetOption() {
        return targetOption;
    }

    public void setTargetOption(ProductOption targetOption) {
        this.targetOption = targetOption;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
