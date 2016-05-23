package commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chanwook
 */
public class Category {

    private List<Product> productList = new ArrayList<>();
    private List<Shop> shopList = new ArrayList<>();

    public void addProduct(Product product) {
        this.productList.add(product);
    }

    public void addShop(Shop shop) {
        this.shopList.add(shop);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<Shop> getShopList() {
        return shopList;
    }
}
