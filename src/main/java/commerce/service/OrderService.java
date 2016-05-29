package commerce.service;

import commerce.model.Cart;
import commerce.model.Order;
import commerce.model.Sku;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chanwook
 */
public class OrderService {

    private ProductRepository productRepository;

    private List<OrderValidator> validatorList = new ArrayList<>();

    public OrderService() {
        // 상품 재고 확인
        validatorList.add(new ProductStockCheckValidator());
    }

    public Order createOrder(Cart cart) {

        List<Sku> orderedSku = productRepository.findSku(cart.toSkuIdList());

        final ValidationSource validationSource =
                new ValidationSource().add("skuList", orderedSku).add("orderRequest", cart);

        // 주문 전처리
        validatorList.forEach(v -> v.validate(validationSource));

        final Order order = new Order();

        // 주문번호 채번 (일단 랜덤하게..) 별도 서비스로 분리하자..
        order.createOrderId();
        cart.getItemList().forEach(i -> order.addItem(i.getSku().getSkuId(), i.getOrderQuantity()));

        return order;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
