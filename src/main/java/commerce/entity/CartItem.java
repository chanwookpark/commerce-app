package commerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author chanwook
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue
    private long cartItemId;

    @ManyToOne(targetEntity = Sku.class, optional = false)
    @JoinColumn(updatable = false, nullable = false, name = "SKU_ITEM_ID")
    private Sku sku;

    @Column(nullable = false, length = 99)
    private int orderQuantity;

    @ManyToOne
    @JoinColumn(updatable = false, nullable = false, name = "CART_ID")
    private Cart cart;

    public CartItem(Sku sku, int orderQuantity) {
        this.sku = sku;
        this.orderQuantity = orderQuantity;
    }
}
