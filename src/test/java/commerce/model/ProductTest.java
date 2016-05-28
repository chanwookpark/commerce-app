package commerce.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
public class ProductTest {

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

    @Test
    public void 상품이미지() throws Exception {


    }
}
