package commerce.model;

import org.junit.Test;

/**
 * @author chanwook
 */
public class CategoryTest {

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

        assert c.getProductList().get(0).getCategory() != null;
        assert c.getProductList().get(1).getCategory() != null;

        assert c.getShopList().get(0).getCategory() != null;
        assert c.getShopList().get(1).getCategory() != null;
        assert c.getShopList().get(2).getCategory() != null;
    }

    /**
     * 카테고리는 부모와 자식 카테고리를 갖는다
     *
     * @throws Exception
     */
    @Test
    public void parentAndChildCategory() throws Exception {

        Category me = new Category();
        final Category parent = new Category();
        final Category child1 = new Category();
        final Category child2 = new Category();

        me.setParentCategory(parent);
        me.addChildCategory(child1);
        me.addChildCategory(child2);

        assert me.getParentCategory().equals(parent);
        assert 2 == me.getChildCategory().size();
        assert me.getChildCategory().get(0).equals(child1);
        assert me.getChildCategory().get(1).equals(child2);
        assert child1.getParentCategory().equals(me);
    }
}
