package commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chanwook
 */
public class Order {

    private long orderId;

    private List<OrderItem> itemList = new ArrayList<>();

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

    public List<OrderItem> getItemList() {
        return itemList;
    }
}
