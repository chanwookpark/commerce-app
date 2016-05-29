package commerce.model;

import commerce.service.OrderService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 고객-주문(상태)-주문아이템-주문아이템가격
 * 주문상태:
 *
 * @author chanwook
 */
public class OrderTest {

    final OrderService orderService = new OrderService();
    final ProductRepositoryMock productMockRepository = new ProductRepositoryMock();

    @Before
    public void setUp() throws Exception {
        orderService.setProductRepository(productMockRepository);
    }

    @Test
    public void 기본적인주문생성() throws Exception {
        final int skuItemId1 = 10;
        final int skuItemId2 = 11;

        productMockRepository.addStock(skuItemId1, 2);
        productMockRepository.addStock(skuItemId2, 2);

        Cart cart = new Cart();
        OrderRequestItem itemRequest1 = new OrderRequestItem(new Sku(skuItemId1), 1);
        OrderRequestItem itemRequest2 = new OrderRequestItem(new Sku(skuItemId2), 2);
        cart.addItem(itemRequest1);
        cart.addItem(itemRequest2);

        Order order = orderService.createOrder(cart);

        assertThat(order).isNotNull();
        assertThat(order.getOrderId()).isNotNull();
        assertThat(order.getItemList().size()).isEqualTo(2);
        assertThat(order.getItemList().get(0).getSkuId()).isEqualTo(skuItemId1);
        assertThat(order.getItemList().get(0).getOrderQuantity()).isEqualTo(1);
        assertThat(order.getItemList().get(1).getSkuId()).isEqualTo(skuItemId2);
        assertThat(order.getItemList().get(1).getOrderQuantity()).isEqualTo(2);

    }
}
