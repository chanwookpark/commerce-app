package commerce.unit;

import commerce.entity.Product;
import commerce.entity.Sku;
import commerce.entity.SkuCreateOption;
import commerce.service.SkuService;
import org.junit.Test;

import java.util.List;

import static commerce.ProductTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
public class SkuTest {

    SkuService ss = new SkuService();

    @Test
    public void 상품옵션조합으로SKU생성() throws Exception {

        final Product product = createTestOnlyProduct();
        product.addOption(createProductOptionToColor());
        product.addOption(createProductOptionToSize());

        List<Sku> skuList = ss.createSku(product, new SkuCreateOption());

        assertThat(skuList.size()).isEqualTo(9);
    }
}
