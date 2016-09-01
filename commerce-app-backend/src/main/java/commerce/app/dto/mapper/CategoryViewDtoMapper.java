package commerce.app.dto.mapper;

import commerce.app.dto.CategoryShopDto;
import commerce.app.entity.Category;

/**
 * @author chanwook
 */
public class CategoryViewDtoMapper {
    public CategoryShopDto createDtoWithBasic(Category entity) {
        CategoryShopDto dto = new CategoryShopDto();

        //TODO 자동 매핑 기능 추가
        dto.setCategoryId(entity.getCategoryId());
        dto.setCategoryLevel(entity.getCategoryLevel());
        dto.setDisplayName(entity.getDisplayName());

        return dto;
    }
}
