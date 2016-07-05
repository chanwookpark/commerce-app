package commerce.service;

import commerce.entity.*;

import java.util.*;

/**
 * @author chanwook
 */
public class SkuService {

    public List<Sku> createSku(Product product, SkuCreateOption skuCreateOption) {
        ProductOptionValueSet set = mixByProductOptionValue(product.getOptionList());
        List<Sku> skuList = createSku(set, skuCreateOption);
        return skuList;
    }

    private List<Sku> createSku(ProductOptionValueSet set, SkuCreateOption skuOption) {
        List<Sku> skuList = new ArrayList<>();
        for (List<ProductOptionValue> valueList : set.get()) {
            Sku sku = new Sku();
            sku.setOptionValueList(valueList);
            skuList.add(sku);
        }
        return skuList;
    }

    private ProductOptionValueSet mixByProductOptionValue(Set<ProductOption> optionList) {

        final ProductOptionValueSet result = new ProductOptionValueSet();

        final Iterator<ProductOption> optionIterator = optionList.iterator();

        while (optionIterator.hasNext()) {
            final ProductOption option = optionIterator.next();
            // 첫 번째 valueList인 경우에는 result가 비어있기 때문에 새 List를 만들어 넣는다
            if (result.isEmpty()) {
                option.getOptionValueList().stream().forEach(v -> result.createElement(v));
            } else {
                Set<List<ProductOptionValue>> newResult = new HashSet<>();
                for (ProductOptionValue value : option.getOptionValueList()) {
                    result.get().stream().forEach(m -> {
                        m.add(value);
                        newResult.add(m);
                    });
                }
                result.replace(newResult);
            }
        }
        return result;
    }

    static class ProductOptionValueSet {
        Set<List<ProductOptionValue>> set = new HashSet<>();

        public Set<List<ProductOptionValue>> get() {
            return set;
        }

        public void replace(Set<List<ProductOptionValue>> set) {
            this.set = set;
        }

        public boolean isEmpty() {
            return this.set.isEmpty();
        }

        public void createElement(ProductOptionValue value) {
            final List<ProductOptionValue> list = new ArrayList<>();
            list.add(value);
            set.add(list);
        }
    }
}
