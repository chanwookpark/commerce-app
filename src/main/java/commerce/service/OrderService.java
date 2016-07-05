package commerce.service;

import commerce.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author chanwook
 */
public class OrderService {

    private ProductRepository productRepository;

    private List<OrderValidator> validatorList = new ArrayList<>();

    public OrderService() {
        // 상품 재고 확인
        validatorList.add(new ProductStockCheckValidator());
    }

    public Order createOrder(Cart cart) {
        final Set<CartItem> itemList = cart.getItemList();
        final Order order = createOrder(itemList);
        return order;
    }

    public Order createOrder(Set<CartItem> itemList) {
        List<Sku> orderedSku = productRepository.findSku(OrderEntityHelper.getSkuIdList(itemList));

        final ValidationSource validationSource =
                new ValidationSource()
                        .add("skuList", orderedSku)
                        .add("orderRequestItemList", itemList);

        // 주문 전처리
        validatorList.forEach(v -> v.validate(validationSource));

        final Order order = new Order();

        // 주문번호 채번 (일단 랜덤하게..) 별도 서비스로 분리하자..
        order.createOrderId();

        addOrderItem(order, itemList);

        order.changeOrderStatus(OrderStatus.OrderStatusCode.A);

        return order;
    }

    public void completeOrder(Order order) {
        //TODO ...

        order.changeOrderStatus(OrderStatus.OrderStatusCode.C);
    }

    /**
     * Cart의 SKU를 OrderItem으로 변환해 생성
     *
     * @param order
     * @param itemList
     */
    private void addOrderItem(Order order, Set<CartItem> itemList) {
        itemList.forEach(i -> order.addItem(i.getSku().getSkuId(), i.getOrderQuantity()));
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Order createRefundOrder(Order originalOrder) {
        RefundOrder refundOrder = new RefundOrder(originalOrder);

        // createOrder를 함께 사용하려고 했는데 어렵겠다...

        // 주문번호 새로 채번
        refundOrder.createOrderId();
        // 기존 주문 속성 정보 복사
        refundOrder.setItemList(originalOrder.getItemList());
        refundOrder.setOrderPrice(originalOrder.getOrderPrice());

        // 환불주문상태 생성
        refundOrder.changeOrderStatus(OrderStatus.OrderStatusCode.D);

        return refundOrder;
    }
}
