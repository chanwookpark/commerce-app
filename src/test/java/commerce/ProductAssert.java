package commerce;

import commerce.entity.ProductOptionValue;
import commerce.entity.Sku;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
public class ProductAssert {
    public static void isEqualsProductOptionValue(ProductOptionValue optionValue, Object... values) {
        assertThat(optionValue).isNotNull();
        assertThat(optionValue.getId()).isEqualTo(values[0]);
        assertThat(optionValue.getTargetOption()).isEqualTo(values[1]);
        assertThat(optionValue.getValue()).isEqualTo(values[2]);
        assertThat(optionValue.getDisplayName()).isEqualTo(values[3]);
    }

    public static void isEqualsToSku(Sku sku, Object... values) {
        assertThat(sku).isNotNull();
        assertThat(sku.getSkuId()).isEqualTo(values[0]);
        final List<ProductOptionValue> valueList = (List) values[1];
        assertThat(sku.getOptionValueList().get(0).getId()).isEqualTo(valueList.get(0).getId());
        assertThat(sku.getOptionValueList().get(1).getId()).isEqualTo(valueList.get(1).getId());
        assertThat(sku.getRetailPrice()).isEqualTo(values[2]);
        assertThat(sku.getSalesPrice()).isEqualTo(values[3]);
        assertThat(sku.getStock()).isEqualTo(values[4]);
    }
}
