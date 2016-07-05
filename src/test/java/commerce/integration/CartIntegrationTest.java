package commerce.integration;

import commerce.CommerceApp;
import commerce.entity.Cart;
import commerce.entity.CartItem;
import commerce.entity.Member;
import commerce.entity.Sku;
import commerce.repository.CartJpaRepository;
import commerce.repository.MemberJpaRepository;
import commerce.repository.SkuJpaRepository;
import commerce.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static commerce.MemberTestSupport.MEMBER_TOR;
import static commerce.MemberTestSupport.getMember;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@SpringBootTest(classes = CommerceApp.class)
public class CartIntegrationTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    CartService cs;

    @Autowired
    MemberJpaRepository mr;

    @Autowired
    CartJpaRepository cr;

    @Autowired
    SkuJpaRepository sr;

    @Test
    public void 장바구니_상품추가() throws Exception {
        final Member member = getMember(MEMBER_TOR);
        mr.save(member);

        final Sku addedItem = new Sku(); //sku 저장 필요
        addedItem.setStock(100); // 판매 가능 재고 100개로...
        addedItem.setRetailPrice(500);
        addedItem.setSalesPrice(400);
        sr.save(addedItem);

        Cart cart = new Cart();
        cart.setOwner(member);

        cs.addProduct(cart, addedItem, 1);

        final Cart persisted = cr.findOne(cart.getCartId());

        assertThat(persisted).isNotNull();
        assertThat(persisted.getItemList().size()).isEqualTo(1);
        final CartItem item1 = persisted.getItemList().iterator().next();
        assertThat(item1.getSku().getSkuId()).isEqualTo(addedItem.getSkuId());
        assertThat(item1.getOrderQuantity()).isEqualTo(1);
    }
}
