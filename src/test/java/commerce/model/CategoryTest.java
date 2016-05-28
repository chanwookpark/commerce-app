package commerce.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

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
    public void 카테고리에상품과매장걸기() throws Exception {
        Category c = new Category();

        c.addProduct(new Product());
        c.addProduct(new Product());

        c.addShop(new Shop());
        c.addShop(new Shop());
        c.addShop(new Shop());

        assert 2 == c.getProductList().size();
        assert 3 == c.getShopList().size();

        assert c.getProductList().get(0).getDisplayCategory() != null;
        assert c.getProductList().get(1).getDisplayCategory() != null;

        assert c.getShopList().get(0).getCategory() != null;
        assert c.getShopList().get(1).getCategory() != null;
        assert c.getShopList().get(2).getCategory() != null;
    }

    /**
     * 카테고리는 부모와 자식 카테고리를 갖는다.
     *
     * @throws Exception
     */
    @Test
    public void 부모와자식카테고리연결() throws Exception {

        Category me = new Category();
        final Category parent = new Category();
        final Category child1 = new Category();
        final Category child2 = new Category();

        me.setParentCategory(parent);
        me.addChildCategory(child1);
        me.addChildCategory(child2);

        assertThat(me.getParentCategory()).isEqualTo(parent);
        assertThat(me.getChildCategory().size()).isEqualTo(2);

        assert me.getChildCategory().get(0).equals(child1);
        assert me.getChildCategory().get(1).equals(child2);
        assert child1.getParentCategory().equals(me);
    }

    /**
     * 카테고리 관계는 '대-중-소-세' 4 단계로 구성한다.
     *
     * @throws Exception
     */
    @Test
    public void 대중소세4단계로구성하는카테고리레벨() throws Exception {

        // 대분류
        final Category c1 = new Category(1, CategoryLevel.A); // 대분류 카테고리

        // 대분류는 상위 카테고리를 갖을 수 없음
        try {
            c1.setParentCategory(new Category());
            fail();
        } catch (Exception e) {
        }

        // 중분류 카테고리
        final Category c2 = new Category(2, CategoryLevel.B);
        c1.addChildCategory(c2);
        c1.addChildCategory(new Category(3, CategoryLevel.B));

        assertThat(c1.getChildCategory().size()).isEqualTo(2);

        // 소분류 카테고리
        final Category c3 = new Category(4, CategoryLevel.C);
        c2.addChildCategory(c3);
        c2.addChildCategory(new Category(5, CategoryLevel.C));

        assertThat(c2.getChildCategory().size()).isEqualTo(2);

        // 세분류 카테고리
        final Category d1 = new Category(6, CategoryLevel.D);
        final Category d2 = new Category(7, CategoryLevel.D);
        d1.setParentCategory(c3);
        d2.setParentCategory(c3);

        assertThat(c3.getChildCategory().size()).isEqualTo(2);
    }
}
