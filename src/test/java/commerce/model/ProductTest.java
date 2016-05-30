package commerce.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
public class ProductTest {

    // 기본속성 추가 필요
    @Test
    public void 상품기본속성() throws Exception {
        Product product = new Product();

        final long productId = 100L;
        final String displayName = "짱짱한 성능! 저렴한 가격! 울트라 최신 노트북!";
        final String productName = "APPLE-노트북-01";
        final Category displayCategory = new Category();

        product.setProductId(productId);
        product.setDisplayName(displayName);
        product.setProductName(productName);
        product.setDisplayCategory(displayCategory);

        assertThat(product).hasFieldOrPropertyWithValue("productId", productId);
        assertThat(product).hasFieldOrPropertyWithValue("displayName", displayName);
        assertThat(product).hasFieldOrPropertyWithValue("productName", productName);
        assertThat(product).hasFieldOrPropertyWithValue("displayCategory", displayCategory);
    }

    final static ProductOption colorOption = new ProductOption(1, "color", "색상");
    final static ProductOption sizeOption = new ProductOption(2, "size", "사이즈");

    @Test
    public void 상품옵션만들기() throws Exception {
        //상품 만들기
        Product product = new Product(100, "운동화", CategoryUtils.createBLevel(1, "나이키"));

        // 상품옵션 만들기 - 컬러, 사이즈
        product.addOption(colorOption);
        product.addOption(sizeOption);

        assertThat(product.getOptionList().size()).isEqualTo(2);
    }

    @Test
    public void 옵션과옵셥값조합으로sku만들기() throws Exception {
        // 상품옵션값 만들기
        final ProductOptionValue colorOptionValue1 = new ProductOptionValue(1001, colorOption, "RED", "빨강색");
        final ProductOptionValue colorOptionValue2 = new ProductOptionValue(1002, colorOption, "BLUE", "파랑색");
        final ProductOptionValue colorOptionValue3 = new ProductOptionValue(1003, colorOption, "YELLOW", "노란색");

        ProductAssert.isEqualsProductOptionValue(colorOptionValue1, 1001L, colorOption, "RED", "빨강색");
        ProductAssert.isEqualsProductOptionValue(colorOptionValue2, 1002L, colorOption, "BLUE", "파랑색");
        ProductAssert.isEqualsProductOptionValue(colorOptionValue3, 1003L, colorOption, "YELLOW", "노란색");

        final ProductOptionValue sizeOptionValue1 = new ProductOptionValue(2001, sizeOption, "XL", "X-Large");

        // sku 생성 (=상품옵션*옵션값) : 생성은 관리도구에서 하겠지!!
        final List<ProductOptionValue> ovl = Arrays.asList(colorOptionValue1, sizeOptionValue1);
        final long retailPrice = 1000;
        final long salesPrice = 900;
        final long stock = 23;

        final Sku sku1 = new Sku(1L, ovl);
        sku1.setRetailPrice(retailPrice);
        sku1.setSalesPrice(salesPrice);
        sku1.setStock(stock);

        ProductAssert.isEqualsToSku(sku1, 1L, ovl, retailPrice, salesPrice, stock);
    }

    @Test
    public void 상품유형추가() throws Exception {
        Product p = new Product();

        // 기본 상품 유형은 일반상품
        assertThat(p.getProductType()).isEqualTo(ProductType.P);

        p.setProductType(ProductType.G);
        assertThat(p.getProductType()).isEqualTo(ProductType.G);

        //TODO 유형 별 제약 조건 추가
        //TODO 엔티티 구조로 변경 검토

    }

    //TODO 재고, 추가 구성상품,
}
