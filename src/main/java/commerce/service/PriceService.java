package commerce.service;

import commerce.model.Order;
import commerce.model.OrderPrice;
import commerce.model.Sku;

/**
 * @author chanwook
 */
public class PriceService {

    private ProductRepository productRepository;

    public OrderPrice makeOrderPrice(Order order) {
        final OrderPrice orderPrice = new OrderPrice();

        order.getItemList().forEach(orderItem -> {
            Sku sku = productRepository.findSku(orderItem.getSkuId());

            final long salesPrice = sku.getSalesPrice();
            orderPrice.addItemPrice(orderItem.getItemId(), salesPrice * orderItem.getOrderQuantity());
        });

        return orderPrice;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }
}
