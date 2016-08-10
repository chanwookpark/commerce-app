package commerce.app.service;

import commerce.app.entity.Product;
import commerce.app.entity.ProductOptionValue;

import java.util.List;

/**
 * @author chanwook
 */
public interface SkuNameStrategy {

    String getName(Product product, List<ProductOptionValue> optionValueList);

}
