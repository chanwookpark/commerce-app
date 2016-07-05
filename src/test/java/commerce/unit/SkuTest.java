package commerce.unit;

import commerce.entity.Product;
import commerce.entity.Sku;
import commerce.entity.SkuCreateOption;
import commerce.service.ProductOptionBaseSkuNameStrategy;
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

        // Option = 1(3)
        product.addOption(createColorOption());
        List<Sku> skuList = ss.createSku(product, new SkuCreateOption());
        assertThat(skuList.size()).isEqualTo(3);

        // Option = 1(3) * 1(3)
        product.addOption(createSizeOption());
        skuList = ss.createSku(product, new SkuCreateOption());
        assertThat(skuList.size()).isEqualTo(9);

        // Option = 1(3) * 1(3) * 1(4)
        product.addOption(createPrintingOption());
        skuList = ss.createSku(product, new SkuCreateOption());
        assertThat(skuList.size()).isEqualTo(36);
    }

    @Test
    public void sku속성생성() throws Exception {
        final Product product = createTestOnlyProduct();
        product.addOption(createColorOption());
        product.addOption(createSizeOption());

        final SkuCreateOption skuCreateOption = new SkuCreateOption();
        skuCreateOption.setDefaultStock(100);
        skuCreateOption.setDefaultRetailPrice(1000);
        skuCreateOption.setDefaultSalePrice(900);
        skuCreateOption.setNameStrategy(new ProductOptionBaseSkuNameStrategy());

        List<Sku> skuList = ss.createSku(product, skuCreateOption);
        assertThat(skuList.size()).isEqualTo(9);

        skuList.forEach(s -> {
            assertThat(s.getStock()).isEqualTo(100);
            assertThat(s.getRetailPrice()).isEqualTo(1000);
            assertThat(s.getSalesPrice()).isEqualTo(900);
            assertThat(s.getSkuDisplayName()).isEqualTo(new ProductOptionBaseSkuNameStrategy().getName(product, s.getOptionValueList()));
        });
    }
}
