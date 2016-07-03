package commerce.service;

import commerce.entity.OrderRequestItem;
import commerce.entity.Sku;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 주문 요청한 재고가 현재 있는지 확인
 *
 * @author chanwook
 */
public class ProductStockCheckValidator implements OrderValidator {


    @Override
    public void validate(ValidationSource validationSource) {
        final List<Sku> skuList = (List<Sku>) validationSource.get("skuList");
        final Set<OrderRequestItem> itemList =
                (Set<OrderRequestItem>) validationSource.get("orderRequestItemList");

        itemList.forEach(item -> {
            final Sku targetSku = getSku(skuList, item.getSku().getSkuId());
            if (!targetSku.hasQuantity(item.getOrderQuantity())) {
                LOGGER.error("주문 가능 재고가 부족해 주문이 정상 접수하지 못했습니다.[SKU번호:" + targetSku.getSkuId() + ", 주문수량:" + item.getOrderQuantity() + ", 재고: " + targetSku.getStock() + "]");
                throw new OrderValidationException("주문 가능 재고가 부족합니다!");
            }
        });
    }

    private Sku getSku(List<Sku> skuList, long skuId) {
        //TODO 한 개 엘리먼트만 뽑는 방법 확인후 코드 정리하자~~
        return skuList.stream().filter(sku -> skuId == sku.getSkuId()).collect(Collectors.toList()).get(0);
    }

}
