package commerce.model;

import commerce.service.OrderService;
import commerce.service.PriceService;
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
    final PriceService priceService = new PriceService();

    final ProductRepositoryMock productMockRepository = new ProductRepositoryMock();

    @Before
    public void setUp() throws Exception {
        orderService.setProductRepository(productMockRepository);
        priceService.setProductRepository(productMockRepository);
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

    @Test
    public void 기본적인가격계산() throws Exception {
        final int skuId1 = 10;
        final int skuId2 = 11;
        productMockRepository.addSku(skuId1, 2, 100);
        productMockRepository.addSku(skuId2, 3, 50);

        // 가격 계산은 주문 아이템이 만들어진 후에 시작

        Order order = new Order();
        order.addItem(skuId1, 1); //개당 100원
        order.addItem(skuId2, 3); //개당 50원

        // 주문 가격 생성
        OrderPrice orderPrice = priceService.makeOrderPrice(order);

        assertThat(orderPrice).isNotNull();
        assertThat(orderPrice.calculate()).isEqualTo(250);
    }
}
