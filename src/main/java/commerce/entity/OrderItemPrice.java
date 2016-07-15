package commerce.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chanwook
 */
@Getter
@Setter
public class OrderItemPrice {

    private long orderItemId;

    private Money orderItemPrice;

    public OrderItemPrice(long orderItemId, Money orderItemPrice) {
        this.orderItemId = orderItemId;
        this.orderItemPrice = orderItemPrice;
    }

}
