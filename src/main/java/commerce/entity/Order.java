package commerce.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chanwook
 */
public class Order {

    private long orderId;

    private List<OrderItem> itemList = new ArrayList<>();

    private OrderPrice orderPrice;

    private OrderStatus orderStatus;

    private Set<OrderStatus> orderHistory = new HashSet<>();

    public void createOrderId() {
        this.orderId = System.nanoTime();
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void addItem(long skuId, int orderQuantity) {
        final OrderItem orderItem = new OrderItem(skuId, orderQuantity);
        this.itemList.add(orderItem);
    }

    public void setOrderPrice(OrderPrice orderPrice) {
        this.orderPrice = orderPrice;
    }

    public OrderPrice getOrderPrice() {
        return orderPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderStatus> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(Set<OrderStatus> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public void changeOrderStatus(OrderStatus.OrderStatusCode orderStatusCode) {
        final OrderStatus orderStatus = new OrderStatus(orderStatusCode, this);
        this.setOrderStatus(orderStatus);
        this.orderHistory.add(orderStatus);
    }

    public OrderItem getItemBySkuId(long skuId) {
        for (OrderItem oi : getItemList()) {
            if (skuId == oi.getSkuId()) return oi;
        }
        return null;
    }
}
