package commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chanwook
 */
public class Category {

    private long categoryId;

    private List<Product> productList = new ArrayList<>();
    private List<Shop> shopList = new ArrayList<>();

    private Category parentCategory;
    private List<Category> childCategory = new ArrayList<>();

    private CategoryLevel categoryLevel;
    private String displayName;

    public Category() {
    }

    public Category(long id, CategoryLevel level) {
        this.categoryId = id;
        this.categoryLevel = level;
    }

    public void addProduct(Product product) {
        this.productList.add(product);
        product.setDisplayCategory(this);
    }

    public void addShop(Shop shop) {
        this.shopList.add(shop);
        shop.setCategory(this);
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<Shop> getShopList() {
        return shopList;
    }


    public void setParentCategory(Category parent) {
        if (CategoryLevel.A.equals(this.categoryLevel)) {
            throw new CategoryException("대분류 카테고리는 상위 카테고리를 지정할 수 없습니다");
        }
        this.parentCategory = parent;
        parentCategory.addChildCategory(this);
    }

    public Category getParentCategory() {
        return parentCategory;
    }


    public void addChildCategory(Category child) {
        // TODO 현재는 객체 비교만... 'ID' 비교로 equals/hash 재정의 필요
        if (childCategory.contains(child)) {
            return;
        }
        this.childCategory.add(child);
        child.setParentCategory(this);
    }

    public List<Category> getChildCategory() {
        return childCategory;
    }


    public void setCategoryLevel(CategoryLevel categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public CategoryLevel getCategoryLevel() {
        return categoryLevel;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
