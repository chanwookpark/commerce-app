package commerce.service;

import commerce.entity.*;

import java.util.*;

/**
 * @author chanwook
 */
public class SkuService {
    public List<Sku> createSku(Product product, SkuCreateOption skuCreateOption) {

        final Set<ProductOption> optionList = product.getOptionList();

        Set<List<ProductOptionValue>> result = new HashSet<>();

        final Iterator<ProductOption> optionIterator = optionList.iterator();
        while (optionIterator.hasNext()) {
            final ProductOption option = optionIterator.next();
            // 첫 번째 valueList인 경우에는 result가 비어있기 때문에 새 List를 만들어 넣는다
            if (result.size() == 0) {
                for (ProductOptionValue value : option.getOptionValueList()) {
                    List<ProductOptionValue> merged = new ArrayList<>();
                    merged.add(value);
                    result.add(merged);
                }
            } else {
                Set<List<ProductOptionValue>> newResult = new HashSet<>();
                for (ProductOptionValue value : option.getOptionValueList()) {
                    final Iterator<List<ProductOptionValue>> resultIterator = result.iterator();
                    while (resultIterator.hasNext()) {
                        final List<ProductOptionValue> merged = resultIterator.next();
                        merged.add(value);
                        newResult.add(merged);
                    }
                }
                result = newResult;
            }
        }

        List<Sku> skuList = new ArrayList<>();
        for (List<ProductOptionValue> valueList : result) {
            Sku sku = new Sku();
            sku.setOptionValueList(valueList);
            skuList.add(sku);
        }
        return skuList;
    }
}
