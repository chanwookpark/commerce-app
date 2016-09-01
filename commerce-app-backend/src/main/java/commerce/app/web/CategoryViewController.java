package commerce.app.web;

import commerce.app.dto.CategoryShopDto;
import commerce.app.dto.mapper.CategoryViewDtoMapper;
import commerce.app.entity.Category;
import commerce.app.repository.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chanwook
 */
@Controller
public class CategoryViewController {

    @Autowired
    CategoryJpaRepository categoryRepository;

    CategoryViewDtoMapper dtoMapper = new CategoryViewDtoMapper();

    @RequestMapping("/category/a/{categoryId}")
    public String viewOfCategoryA(@RequestParam("categoryId") Long categoryId, ModelMap model) {

        // 기본 정보 조회
        final Category category = categoryRepository.findOne(categoryId);
        CategoryShopDto dto = dtoMapper.createDtoWithBasic(category);

        // 하위 카테고리 조회

        // 매장 조회

        // 상품 조회

        model.put("categoryShop", dto);
        return "category/categoryA";
    }
}
