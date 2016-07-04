package commerce.entity;

import commerce.service.CategoryException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chanwook
 */
@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    private long categoryId;

    @OneToMany(mappedBy = "displayCategory")
    private List<Product> productList = new ArrayList<>();

    @OneToMany
    private List<Shop> shopList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
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

    public void setParentCategory(Category parent) {
        if (CategoryLevel.A.equals(this.categoryLevel)) {
            throw new CategoryException("대분류 카테고리는 상위 카테고리를 지정할 수 없습니다");
        }
        this.parentCategory = parent;
        parentCategory.addChildCategory(this);
    }

    public void addChildCategory(Category child) {
        // TODO 현재는 객체 비교만... 'ID' 비교로 equals/hash 재정의 필요
        if (childCategory.contains(child)) {
            return;
        }
        this.childCategory.add(child);
        child.setParentCategory(this);
    }

}
