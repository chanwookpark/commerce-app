package commerce;

import commerce.entity.Sku;
import commerce.service.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chanwook
 */
public class ProductRepositoryMock implements ProductRepository {
    private Map<Long, Sku> skuMap = new HashMap<>();

    public void addStock(long skuId, int additionalStock) {
        if (!skuMap.containsKey(skuId)) {
            skuMap.put(skuId, new Sku(skuId));
        }

        final Sku sku = skuMap.get(skuId);
        long newStock = sku.getStock() + additionalStock;
        sku.setStock(newStock);
    }

    @Override
    public List<Sku> findSku(List<Long> skuIdList) {
        // TODO ID에 해당하는 SKU가 모두 없을 때는??
        return skuIdList.stream().map(id -> skuMap.get(id)).collect(Collectors.toList());
    }

    @Override
    public Sku findSku(long skuId) {
        return skuMap.get(skuId);
    }

    public void addSku(long skuId, long stock, long salePrice) {
        this.skuMap.put(skuId, new Sku(skuId, stock, salePrice));
    }
}
