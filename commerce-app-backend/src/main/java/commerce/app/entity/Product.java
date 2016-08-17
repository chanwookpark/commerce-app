package commerce.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chanwook
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Product {

    @Id
//    @GeneratedValue //FIXME 교체
    private String productId;

    private String displayName;

    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISPLAY_CATEGORY_ID")
    private Category displayCategory;

    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private ProductType productType = ProductType.P;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "PRD_PRD_OPTION",
            joinColumns = {@JoinColumn(name = "productId")},
            inverseJoinColumns = {@JoinColumn(name = "optionId")})
    private Set<ProductOption> optionList = new HashSet<>();

    public Product() {
    }

    public Product(String productId, String productName, Category displayCategory) {
        this.productId = productId;
        this.productName = productName;
        this.displayCategory = displayCategory;
    }

    public void addOption(ProductOption option) {
        this.optionList.add(option);
    }

}

