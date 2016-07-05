package commerce.service;

import commerce.entity.Cart;
import commerce.entity.CartItem;
import commerce.entity.Sku;
import commerce.repository.CartJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chanwook
 */
@Service
public class CartService {

    CartJpaRepository cartRepository;

    @Transactional
    public void addProduct(Cart cart, Sku addedItem, int orderQuantity) {

        //FIXME 다른 로직과 어떻게 통합할 것인가?? 해당 시점의 데이터 단위와 개별 로직의 단위를 맞출 수 있는 지점 필요
        if (!addedItem.hasQuantity(orderQuantity)) {
            throw new CartException("상품 주문 재고가 충분하지 않습니다.(잔여 재고:" + addedItem.getStock() + ", 주문요청수량:" + orderQuantity + ")");
        }

        //TODO 로직..로직..로직..

        cart.addItem(new CartItem(addedItem, orderQuantity));

        cartRepository.save(cart);
    }

    @Autowired
    public void setCartRepository(CartJpaRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
}
