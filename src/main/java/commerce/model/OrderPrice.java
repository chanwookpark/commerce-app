package commerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chanwook
 */
public class OrderPrice {

    private List<OrderItemPrice> orderItemPriceList = new ArrayList<>();

    public void addItemPrice(long itemId, long orderItemPrice) {
        this.orderItemPriceList.add(new OrderItemPrice(itemId, orderItemPrice));
    }

    public List<OrderItemPrice> getOrderItemPriceList() {
        return orderItemPriceList;
    }

    public long calculate() {
        return orderItemPriceList.stream().mapToLong(OrderItemPrice::getOrderItemPrice).sum();
    }
}
