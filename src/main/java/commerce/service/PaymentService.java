package commerce.service;

import commerce.Order;
import commerce.OrderStatus;

/**
 * @author chanwook
 */
public class PaymentService {
    public void completePayment(Order order) {
        //TODO 흠.. 어찌할지는 고민...

        order.changeOrderStatus(OrderStatus.OrderStatusCode.B);
    }
}
