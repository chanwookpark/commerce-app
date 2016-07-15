package commerce;

import commerce.entity.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
public class ProductTestSupport {

    static ProductIdGenerator productIdGenerator = new TimebaseProductIdGenerator();

    //일단 하나를 만들고 앞으로 어떻게 할지는 고민해보자
    public static Product createTestOnlyProduct() {

        Product product = new Product();
        product.setProductId(productIdGenerator.createId());
        product.setProductName("초대박 히트 티셔츠");
        product.setProductType(ProductType.P);
        product.setDisplayName("2016년 최신상 최고급 티셔츠");

        return product;
    }

    public static ProductOption createColorOption() {
        ProductOption option = new ProductOption();
        option.setOptionName("표준 티셔츠 색상");
        option.setDisplayName("색상");

        option.addProductOptionValue(new ProductOptionValue("RED", "빨간색"));
        option.addProductOptionValue(new ProductOptionValue("BLUE", "파란색"));
        option.addProductOptionValue(new ProductOptionValue("YELLOW", "노란색"));
        return option;
    }

    public static ProductOption createSizeOption() {
        ProductOption option = new ProductOption();
        option.setDisplayName("사이즈");
        option.setOptionName("표준 티셔츠 사이즈");

        option.addProductOptionValue(new ProductOptionValue("M", "M"));
        option.addProductOptionValue(new ProductOptionValue("L", "L"));
        option.addProductOptionValue(new ProductOptionValue("XL", "XL"));
        return option;
    }

    public static ProductOption createPrintingOption() {
        final ProductOption option = new ProductOption();
        option.setDisplayName("프린팅");
        option.setOptionName("어벤저스 티셔츠 프린팅");

        option.addProductOptionValue(new ProductOptionValue("tor", "토르"));
        option.addProductOptionValue(new ProductOptionValue("ironman", "아이언맨"));
        option.addProductOptionValue(new ProductOptionValue("hulk", "헐크"));
        option.addProductOptionValue(new ProductOptionValue("captain", "캡틴"));
        return option;
    }

    public static void isEqualsProductOptionValue(ProductOptionValue optionValue, Object... values) {
        assertThat(optionValue).isNotNull();
        assertThat(optionValue.getValueId()).isEqualTo(values[0]);
        assertThat(optionValue.getTargetOption()).isEqualTo(values[1]);
        assertThat(optionValue.getValue()).isEqualTo(values[2]);
        assertThat(optionValue.getDisplayName()).isEqualTo(values[3]);
    }

    public static void isEqualsToSku(Sku sku, Object... values) {
        assertThat(sku).isNotNull();
        assertThat(sku.getSkuId()).isEqualTo(values[0]);
        final List<ProductOptionValue> valueList = (List) values[1];
        assertThat(sku.getOptionValueList().get(0).getValueId()).isEqualTo(valueList.get(0).getValueId());
        assertThat(sku.getOptionValueList().get(1).getValueId()).isEqualTo(valueList.get(1).getValueId());
        assertThat(sku.getRetailPrice().getAmount()).isEqualTo(values[2]);
        assertThat(sku.getSalesPrice().getAmount()).isEqualTo(values[3]);
        assertThat(sku.getStock()).isEqualTo(values[4]);
    }

}
