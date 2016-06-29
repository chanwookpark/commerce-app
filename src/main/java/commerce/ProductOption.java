package commerce;

/**
 * @author chanwook
 */
public class ProductOption {

    private int optionId;

    private String optionName;

    private String displayName;

    public ProductOption(int id, String name, String displayName) {
        this.optionId = id;
        this.optionName = name;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}
