package commerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chanwook
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private Member owner;

    final Set<OrderRequestItem> itemList = new HashSet<>();

    public void addItem(OrderRequestItem itemRequest) {
        this.itemList.add(itemRequest);
    }
}
