package commerce.service;

import commerce.entity.Money;
import commerce.entity.Order;
import commerce.entity.OrderPrice;
import commerce.entity.Sku;

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
