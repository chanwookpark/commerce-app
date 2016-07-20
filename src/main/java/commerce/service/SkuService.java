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
        List<Sku> skuList = createSku(product, set, skuCreateOption);
        return skuList;
    }

    private ProductOptionValueSet mixingProductOptionValue(Set<ProductOption> optionList) {
        final ProductOptionValueSet set = new ProductOptionValueSet();
        optionList.forEach(option -> {
            final List<ProductOptionValue> valueList = option.getOptionValueList();
            if (set.isEmpty()) {
                /**
                 * 첫 번째 valueList인 경우에는 result가 비어있기 때문에 새 List를 만들어 넣는다
                 */
                valueList.forEach(v -> set.newListAndMerge(v));
            } else {
                /**
                 * 현재 새로운 ProductOption의 모든 Value와 조합한 새로운 set을 만들고 최종 set을 이걸로 교체
                 */
                Set<List<ProductOptionValue>> currentSet = set.get();
                ProductOptionValueSet mergedSet = new ProductOptionValueSet();

                valueList.forEach(v -> currentSet.forEach(list -> mergedSet.addListAndMerge(list, v)));
                set.replaceTo(mergedSet);
            }
        });
        return set;
    }

    private List<Sku> createSku(Product product, ProductOptionValueSet set, SkuCreateOption skuOption) {
        List<Sku> skuList = new ArrayList<>();
        for (List<ProductOptionValue> valueList : set.get()) {
            Sku sku = new Sku();
            sku.setOptionValueList(valueList);
            sku.setDisplayName(skuOption.getNameStrategy().getName(product, valueList));
            sku.setStock(skuOption.getDefaultStock());
            sku.setRetailPrice(new Money(skuOption.getDefaultRetailPrice()));
            sku.setSalesPrice(new Money(skuOption.getDefaultSalePrice()));
            sku.setProduct(product);

            skuList.add(sku);
        }
        return skuList;
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

        public void newListAndMerge(ProductOptionValue value) {
            final List<ProductOptionValue> list = new ArrayList<>();
            addListAndMerge(list, value);
        }

        public void addListAndMerge(List<ProductOptionValue> list, ProductOptionValue... values) {
            Stream.of(values).forEach(v -> list.add(v));
            set.add(list);
        }
    }
}
