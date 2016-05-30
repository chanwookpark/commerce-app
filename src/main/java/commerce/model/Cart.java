package commerce.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<Long> toSkuIdList() {
        return itemList.stream().map(i -> i.getSku().getSkuId()).collect(Collectors.toList());
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public Member getOwner() {
        return owner;
    }
}
