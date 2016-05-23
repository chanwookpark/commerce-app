package commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chanwook
 */
public class Category {

    private List<Product> productList = new ArrayList<>();
    private List<Shop> shopList = new ArrayList<>();

    private Category parentCategory;
    private List<Category> childCategory = new ArrayList<>();

    public void addProduct(Product product) {
        this.productList.add(product);
        product.setCategory(this);
    }

    public void addShop(Shop shop) {
        this.shopList.add(shop);
        shop.setCategory(this);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<Shop> getShopList() {
        return shopList;
    }


    public void setParentCategory(Category parent) {
        this.parentCategory = parent;
    }

    public Category getParentCategory() {
        return parentCategory;
    }


    public void addChildCategory(Category child) {
        this.childCategory.add(child);
        child.setParentCategory(this);
    }

    public List<Category> getChildCategory() {
        return childCategory;
    }
}
