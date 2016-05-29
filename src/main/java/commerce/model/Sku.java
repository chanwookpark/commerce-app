package commerce.model;

import java.util.List;

/**
 * @author chanwook
 */
public class Sku {
    private long skuId;

    private List<ProductOptionValue> optionValueList;
    private long retailPrice;
    private long salesPrice;
    private long stock;

    public Sku(long skuId, List<ProductOptionValue> optionValueList) {
        this.skuId = skuId;
        this.optionValueList = optionValueList;
    }

    public List<ProductOptionValue> getOptionValueList() {
        return optionValueList;
    }

    public void setOptionValueList(List<ProductOptionValue> optionValueList) {
        this.optionValueList = optionValueList;
    }

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(long skuId) {
        this.skuId = skuId;
    }

    public void setRetailPrice(long retailPrice) {
        this.retailPrice = retailPrice;
    }

    public long getRetailPrice() {
        return retailPrice;
    }

    public void setSalesPrice(long salesPrice) {
        this.salesPrice = salesPrice;
    }

    public long getSalesPrice() {
        return salesPrice;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public long getStock() {
        return stock;
    }
}
