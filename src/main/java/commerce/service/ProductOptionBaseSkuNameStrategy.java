package commerce.service;

import commerce.entity.Product;
import commerce.entity.ProductOptionValue;

import java.util.List;

/**
 * @author chanwook
 */
public class ProductOptionBaseSkuNameStrategy implements SkuNameStrategy {

    public String getName(Product product, List<ProductOptionValue> optionValueList) {
        StringBuilder sb = new StringBuilder();
        optionValueList.stream()
                .map(ProductOptionValue::getTargetOption)
                .forEach(o -> sb.append(o.getDisplayName() + "-"));
        sb.deleteCharAt(sb.lastIndexOf("-")); //FIXME 흠...
        return sb.toString();
    }

}
