package commerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue
    private long cartId;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false, name = "OWNER_MEMBER_ID")
    private Member owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    final Set<CartItem> itemList = new HashSet<>();

    public void addItem(CartItem item) {
        if (item.getCart() == null || item.getCart().getCartId() != cartId) {
            item.setCart(this);
        }
        this.itemList.add(item);
    }
}
