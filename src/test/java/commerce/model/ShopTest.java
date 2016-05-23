package commerce.model;

import org.junit.Test;

/**
 * @author chanwook
 */
public class ShopTest {
    /**
     * 하나의 카테고리에는 여러 개의 상품과 매장을 걸 수 있다.
     * 여기서 매장은 제네릭한 매장임.
     *
     * @throws Exception
     */
    @Test
    public void categoryBindingWithProductAndShop() throws Exception {
        Category c = new Category();

        c.addProduct(new Product());
        c.addProduct(new Product());

        c.addShop(new Shop());
        c.addShop(new Shop());
        c.addShop(new Shop());

        assert 2 == c.getProductList().size();
        assert 3 == c.getShopList().size();

    }
}
