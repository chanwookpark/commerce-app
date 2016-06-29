package commerce.service;

import commerce.Cart;
import commerce.OrderRequestItem;
import commerce.Sku;

/**
 * @author chanwook
 */
public class CartService {

    public void addProduct(Cart cart, Sku addedItem, int orderQuantity) {

        //FIXME 다른 로직과 어떻게 통합할 것인가?? 해당 시점의 데이터 단위와 개별 로직의 단위를 맞출 수 있는 지점 필요
        if (!addedItem.hasQuantity(orderQuantity)) {
            throw new CartException("상품 주문 재고가 충분하지 않습니다.(잔여 재고:" + addedItem.getStock() + ", 주문요청수량:" + orderQuantity + ")");
        }

        //TODO 로직..로직..로직..

        cart.addItem(new OrderRequestItem(addedItem, orderQuantity));

    }
}
