package commerce.unit;

import commerce.entity.Cart;
import commerce.entity.Member;
import commerce.entity.Sku;
import commerce.repository.CartJpaRepository;
import commerce.service.CartService;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
public class CartTest {

    @Test
    public void 장바구니_생성_상품추가() throws Exception {
        CartService cartService = new CartService();
        cartService.setCartRepository(Mockito.mock(CartJpaRepository.class));

        final String memberId = "chanwook";
        final int skuId = 10;

        // 장바구니 정보 자체는 DB에 저장하지 않고 new로 생성해서 세션에 관리하는 것으로 한다 (현재는 말이지..) -> DB로 바뀜..
        Cart cart = new Cart();
        cart.setOwner(new Member(memberId));
        // 장바구니에 추가하는 건 Product가 아니라 SKU 단위

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
