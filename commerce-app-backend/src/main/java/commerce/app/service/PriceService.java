package commerce.app.service;

import commerce.app.entity.Order;
import commerce.app.entity.OrderPrice;
import commerce.app.entity.Sku;
import commerce.app.entity.Money;

/**
 * @author chanwook
 */
public class PriceService {

    private ProductRepository productRepository;

    public OrderPrice makeOrderPrice(Order order) {
        final OrderPrice orderPrice = new OrderPrice();

        order.getItemList().forEach(orderItem -> {
            Sku sku = productRepository.findSku(orderItem.getSkuId());

            final Money salesPrice = sku.getSalesPrice();
            orderPrice.addItemPrice(orderItem.getItemId(), salesPrice.multiply(orderItem.getOrderQuantity()));
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
