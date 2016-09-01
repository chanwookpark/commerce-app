package commerce.app.web;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import commerce.app.TestConfig;
import commerce.app.dto.CategoryShopDto;
import commerce.app.dto.ChildCategoryDto;
import commerce.app.entity.CategoryLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ModelMap;

import java.util.List;

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
    public void 대분류카테고리매장화면_기본카테고리정보확인() throws Exception {
        //given
        final long categoryId = 5000L;

        //when
        final ModelMap modelMap = new ModelMap();
        final String view = controller.viewOfCategoryA(categoryId, modelMap);

        //then
        assertThat(view).isEqualTo("category/categoryA");

        final CategoryShopDto dto = (CategoryShopDto) modelMap.get("categoryShop");
        assertThat(dto.getCategoryId()).isEqualTo(categoryId);
        assertThat(dto.getDisplayName()).isEqualTo("패션/의류");
        assertThat(dto.getCategoryLevel()).isEqualTo(CategoryLevel.A);
    }

    @Test
    public void 대분류카테고리매장화면_유효한하위카테고리조회() throws Exception {
        //given
        final long categoryId = 5000L;

        //when
        final ModelMap model = new ModelMap();
        final String view = controller.viewOfCategoryA(categoryId, model);

        //then
        assertThat(view).isEqualTo("category/categoryA");
        final CategoryShopDto dto = (CategoryShopDto) model.get("categoryShop");

        final List<ChildCategoryDto> childList = dto.getChildCategoryList();
        assertThat(childList.size()).isEqualTo(4);
        assertThat(childList.get(0).getCategoryId()).isEqualTo(5101);
        assertThat(childList.get(1).getCategoryId()).isEqualTo(5102);
        assertThat(childList.get(2).getCategoryId()).isEqualTo(5103);
        assertThat(childList.get(3).getCategoryId()).isEqualTo(5111);
    }
}
