package commerce.entity;

/**
 * @author chanwook
 */
public class OrderRequestItem {

    private final Sku sku;
    private final int orderQuantity;

    public OrderRequestItem(Sku targetSku, int orderQuantity) {
        this.sku = targetSku;
        this.orderQuantity = orderQuantity;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public Sku getSku() {
        return sku;
    }
}
