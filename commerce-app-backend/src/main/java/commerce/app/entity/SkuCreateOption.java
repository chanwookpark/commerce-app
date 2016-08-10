package commerce.app.entity;

import commerce.app.service.ProductOptionBaseSkuNameStrategy;
import commerce.app.service.SkuNameStrategy;

/**
 * @author chanwook
 */
public class SkuCreateOption {

    private int defaultStock;

    private int defaultRetailPrice;

    private int defaultSalePrice;

    private SkuNameStrategy nameStrategy = new ProductOptionBaseSkuNameStrategy();

    public void setDefaultStock(int defaultStock) {
        this.defaultStock = defaultStock;
    }

    public int getDefaultStock() {
        return defaultStock;
    }

    public void setDefaultRetailPrice(int defaultRetailPrice) {
        this.defaultRetailPrice = defaultRetailPrice;
    }

    public int getDefaultRetailPrice() {
        return defaultRetailPrice;
    }


    public void setDefaultSalePrice(int defaultSalePrice) {
        this.defaultSalePrice = defaultSalePrice;
    }

    public int getDefaultSalePrice() {
        return defaultSalePrice;
    }


    public void setNameStrategy(SkuNameStrategy nameStrategy) {
        this.nameStrategy = nameStrategy;
    }

    public SkuNameStrategy getNameStrategy() {
        return nameStrategy;
    }
}
