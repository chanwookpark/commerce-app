package commerce;

import commerce.service.CartService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
public class CartTest {

    @Test
    public void 장바구니상품추가() throws Exception {
        final String memberId = "chanwook";
        final int skuId = 10;

        // 장바구니에 추가하는 건 Product가 아니라 SKU 단위
        Cart cart = new Cart();
        cart.setOwner(new Member(memberId));

        CartService cartService = new CartService();
        cartService.addProduct(cart, new Sku(skuId, 5, 110), 2);

        assertThat(cart.getOwner().getMemberId()).isEqualTo(memberId);
        assertThat(cart.getItemList().size()).isEqualTo(1);
        assertThat(cart.getItemList().iterator().next().getSku().getSkuId()).isEqualTo(skuId);
    }

    @Test
    public void 상품유형별장바구니생성() throws Exception {
        // 상품 유형이 일단 필요하겠지.. (ok)

    }
}
