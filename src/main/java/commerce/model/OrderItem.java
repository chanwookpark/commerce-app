package commerce.model;

/**
 * @author chanwook
 */
public class OrderItem {

    private long itemId;

    private long skuId;

    private int orderQuantity;


    public OrderItem(long skuId, int orderQuantity) {
        this.skuId = skuId;
        this.orderQuantity = orderQuantity;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(long skuId) {
        this.skuId = skuId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
