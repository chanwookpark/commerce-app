package commerce;

/**
 * @author chanwook
 */
public class OrderItemPrice {

    private long orderItemId;

    private long orderItemPrice;

    public OrderItemPrice(long orderItemId, long orderItemPrice) {
        this.orderItemId = orderItemId;
        this.orderItemPrice = orderItemPrice;
    }

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public long getOrderItemPrice() {
        return orderItemPrice;
    }

    public void setOrderItemPrice(long orderItemPrice) {
        this.orderItemPrice = orderItemPrice;
    }
}
