package commerce.app.integration;

import commerce.app.ProductTestSupport;
import commerce.app.TestConfig;
import commerce.app.entity.Product;
import commerce.app.entity.ProductOption;
import commerce.app.repository.ProductJpaRepository;
import commerce.app.repository.ProductOptionJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@SpringBootTest(classes = TestConfig.class)
public class ProductIntegrationTest {

    @Autowired
    ProductJpaRepository pr;

    @Autowired
    ProductOptionJpaRepository por;

    @Autowired
    TestEntityManager em;

    @Test
    public void 상품과상품옵션생성및조합() throws Exception {

        final ProductOption sizeOption = por.save(ProductTestSupport.createSizeOption());
        final ProductOption colorOption = por.save(ProductTestSupport.createColorOption());

        assertThat(por.findAll().size()).isEqualTo(2);
        assertThat(por.findOne(sizeOption.getOptionId())).isNotNull();
        assertThat(por.findOne(colorOption.getOptionId())).isNotNull();

        final Product product = ProductTestSupport.createTestOnlyProduct();
        product.addOption(sizeOption);
        product.addOption(colorOption);

        pr.save(product);
        final Product persistedProduct = em.find(Product.class, product.getProductId());

        assertThat(persistedProduct).isNotNull();
        assertThat(persistedProduct.getProductId()).isEqualTo(product.getProductId());
    }
}
