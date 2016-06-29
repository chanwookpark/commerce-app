package commerce;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chanwook
 */
public class Cart {

    private Member owner;

    final Set<OrderRequestItem> itemList = new HashSet<>();

    public void addItem(OrderRequestItem itemRequest) {
        this.itemList.add(itemRequest);
    }

    public Set<OrderRequestItem> getItemList() {
        return itemList;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public Member getOwner() {
        return owner;
    }
}
