package commerce.service;

import commerce.entity.ProductOptionValue;

import java.util.List;

/**
 * @author chanwook
 */
public interface SkuNameStrategy {

    String getName(List<ProductOptionValue> optionValueList);

}
