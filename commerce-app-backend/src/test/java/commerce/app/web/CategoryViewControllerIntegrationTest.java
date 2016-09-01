package commerce.app.web;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import commerce.app.TestConfig;
import commerce.app.dto.CategoryShopDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ModelMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chanwook
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
        listeners = {DbUnitTestExecutionListener.class})
@DatabaseSetup("/sample-data.xml")
public class CategoryViewControllerIntegrationTest {

    @Autowired
    CategoryViewController controller;

    @Test
    public void 대분류매장화면_유효한하위카테고리조회() throws Exception {
        //given
        final long categoryId = 5000L;
        final ModelMap model = new ModelMap();

        //when
        final String view = controller.viewOfCategoryA(categoryId, model);

        //then
        assertThat(view).isEqualTo("category/categoryA");
        final CategoryShopDto dto = (CategoryShopDto) model.get("categoryShop");
        assertThat(dto.getChildCategoryList().size()).isEqualTo(4);
    }
}
