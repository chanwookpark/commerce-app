package commerce.model;

import commerce.service.OrderService;
import commerce.service.PaymentService;
import commerce.service.PriceService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

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
    final PaymentService paymentService = new PaymentService();

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

    @Test
    public void 주문상태처리() throws Exception {
        // 주문 생성 단계
        final Order order = orderService.createOrder(new Cart());
        assertOrderStatus(order, OrderStatus.OrderStatusCode.A, 1);

        // 주문 결재 완료 상태 -> 직접 환불 가능 상태
        paymentService.completePayment(order);
        assertOrderStatus(order, OrderStatus.OrderStatusCode.B, 2);

        // 주문 처리 완료 상태 (판매자 확인 상태) -> 판매자 확인 후 환불/교환 가능
        orderService.completeOrder(order);
        assertOrderStatus(order, OrderStatus.OrderStatusCode.C, 3);

        /* 배송 상태는 별도로 관리 */

        // 환불 주문 상태 생성
        Order refundOrder = orderService.createRefundOrder(order);
        assertOrderStatus(refundOrder, OrderStatus.OrderStatusCode.D, 1);
        assertOrderStatus(order, OrderStatus.OrderStatusCode.C, 3); //원주문은 주문완료 상태로 유지

        // 반품/교환
        //TODO

    }

    //TODO 환불, 반품교환 주문 처리

    private void assertOrderStatus(Order order, OrderStatus.OrderStatusCode orderStatusCode, int expectedStatusHistory) throws InterruptedException {
        final OrderStatus orderStatus = order.getOrderStatus();

        assertThat(orderStatus.getOrder().getOrderId()).isEqualTo(order.getOrderId());
        assertThat(orderStatus.getCode()).isEqualTo(orderStatusCode);
        Thread.sleep(100);
        assertThat(orderStatus.getUpdated().isBefore(LocalDateTime.now())).isTrue(); //FIXME 더 나이스한 방법은?
        assertThat(order.getOrderHistory().size()).isEqualTo(expectedStatusHistory);
    }
}
