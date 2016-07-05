package commerce.service;

import commerce.entity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author chanwook
 */
public class SkuService {

    public List<Sku> createSku(Product product, SkuCreateOption skuCreateOption) {
        ProductOptionValueSet set = mixingProductOptionValue(product.getOptionList());
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

    private ProductOptionValueSet mixingProductOptionValue(Set<ProductOption> optionList) {

        final ProductOptionValueSet set = new ProductOptionValueSet();
        optionList.forEach(option -> {
            // 첫 번째 valueList인 경우에는 result가 비어있기 때문에 새 List를 만들어 넣는다
            if (set.isEmpty()) {
                option.getOptionValueList().forEach(v -> set.newValueList(v));
            } else {
                Set<List<ProductOptionValue>> currentSet = set.get();
                ProductOptionValueSet newSet = new ProductOptionValueSet();

                option.getOptionValueList().forEach(v -> {
                    currentSet.forEach(list -> newSet.addListAfterMerge(list, v));
                });
                set.replaceTo(newSet);
            }
        });
        return set;
    }

    static class ProductOptionValueSet {
        Set<List<ProductOptionValue>> set = new HashSet<>();

        public Set<List<ProductOptionValue>> get() {
            return set;
        }

        public void replaceTo(ProductOptionValueSet set) {
            this.set = set.get();
        }

        public boolean isEmpty() {
            return this.set.isEmpty();
        }

        public void newValueList(ProductOptionValue value) {
            final List<ProductOptionValue> list = new ArrayList<>();
            list.add(value);
            set.add(list);
        }

        public void addListAfterMerge(List<ProductOptionValue> list, ProductOptionValue... values) {
            Stream.of(values).forEach(v -> list.add(v));
            set.add(list);
        }
    }
}
