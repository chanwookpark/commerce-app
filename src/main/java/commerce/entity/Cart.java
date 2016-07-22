package commerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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

    @OneToOne
    @JoinColumn(name = "OWNER_MEMBER_NUMBER", referencedColumnName = "memberNumber",nullable = false)
    private Member owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    final Set<CartItem> itemList = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public void addItem(CartItem item) {
        if (item.getCart() == null || item.getCart().getCartId() != cartId) {
            item.setCart(this);
        }
        this.itemList.add(item);
    }

    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.setCreated(new Date()); // 아직 LocalDate를 JPA에서 미지원...
        cart.setOwner(member);
        member.setCart(cart);
        return cart;
    }
}
