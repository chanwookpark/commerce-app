package commerce.model;

/**
 * 환불주문 엔티티
 *
 * @author chanwook
 */
public class RefundOrder extends Order {

    private Order originalOrder;


    public RefundOrder(Order originalOrder) {
        this.originalOrder = originalOrder;
    }
}
