package commerce.app.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chanwook
 */
@Getter
public class OrderPrice {

    private List<OrderItemPrice> orderItemPriceList = new ArrayList<>();

    public void addItemPrice(long itemId, Money orderItemPrice) {
        this.orderItemPriceList.add(new OrderItemPrice(itemId, orderItemPrice));
    }

    public long calculate() {
        return orderItemPriceList.stream()
                .map(OrderItemPrice::getOrderItemPrice)
                .mapToLong(Money::getAmount)
                .sum();
    }
}
