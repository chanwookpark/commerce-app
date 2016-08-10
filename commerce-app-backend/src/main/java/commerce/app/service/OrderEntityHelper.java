package commerce.app.service;

import commerce.app.entity.CartItem;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chanwook
 */
public class OrderEntityHelper {

    public static List<Long> getSkuIdList(Collection<CartItem> itemList) {
        return itemList.stream().map(i -> i.getSku().getSkuId()).collect(Collectors.toList());
    }
}
