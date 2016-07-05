package commerce.service;

import commerce.entity.Product;
import commerce.entity.ProductOptionValue;

import java.util.List;

/**
 * @author chanwook
 */
public interface SkuNameStrategy {

    String getName(Product product, List<ProductOptionValue> optionValueList);

}
