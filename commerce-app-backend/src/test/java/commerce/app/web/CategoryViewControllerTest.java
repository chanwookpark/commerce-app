package commerce.app.web;

import commerce.app.dto.CategoryShopDto;
import commerce.app.entity.Category;
import commerce.app.entity.CategoryLevel;
import commerce.app.repository.CategoryJpaRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author chanwook
 */
public class CategoryViewControllerTest {

    CategoryViewController controller;
    CategoryJpaRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        //TODO mvc 테스트FW 적용
        controller = new CategoryViewController();
        categoryRepository = mock(CategoryJpaRepository.class);
        controller.categoryRepository = categoryRepository;
    }

    @Test
    public void 대분류카테고리매장화면() throws Exception {
        //given
        final long categoryId = 1000L;
        final String displayName = "테스트매장A";
        final CategoryLevel categoryLevel = CategoryLevel.A;

        mockingWhenFindOneOfCategory(categoryId, displayName, categoryLevel);

        //when
        final ModelMap modelMap = new ModelMap();
        final String view = controller.viewOfCategoryA(categoryId, modelMap);

        //then
        assertThat(view).isEqualTo("category/categoryA");

        final CategoryShopDto dto = (CategoryShopDto) modelMap.get("categoryShop");
        assertThat(dto.getCategoryId()).isEqualTo(categoryId);
        assertThat(dto.getDisplayName()).isEqualTo(displayName);
        assertThat(dto.getCategoryLevel()).isEqualTo(categoryLevel);
    }

    private void mockingWhenFindOneOfCategory(long categoryId, String displayName, CategoryLevel categoryLevel) {
        final Category expectedCategory = new Category(categoryId, displayName, categoryLevel);
        when(categoryRepository.findOne(categoryId)).thenReturn(expectedCategory);
    }
}
