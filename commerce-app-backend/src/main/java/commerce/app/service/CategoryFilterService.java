package commerce.app.service;

import commerce.app.dto.ChildCategoryDto;
import commerce.app.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chanwook
 */
public class CategoryFilterService {

    private final Logger logger = LoggerFactory.getLogger(CategoryFilterService.class);

    //TODO 통으로 개선
    public List<ChildCategoryDto> filteringValidChildCategory(Category parentCategory, List<Category> candidateChildCategoryList) {
        final List<ChildCategoryDto> validChildCategoryList = new ArrayList<>();
        for (Category child : candidateChildCategoryList) {
            if (isValidatedRelationship(parentCategory, child)) {
                logger.info("카테고리 관계가 부적절해 하위 카테고리 정보로 노출하지 않습니다. " +
                        "관련 정보를 확인해보세요! (parentCategory.getCategoryId() -> {})", child.getCategoryId());
                continue;
            }

            //TODO 상품 없으면 노출하지 말까??

            if (isValidExposeDateTime(child)) {
                logger.info("노출 시간이 해당하지 않는 카테고리입니다! (제외된 카테고리ID: {})", child.getCategoryId());
                continue;
            }
            validChildCategoryList.add(new ChildCategoryDto(child.getCategoryId(), child.getDisplayName()));
        }
        return validChildCategoryList;
    }

    private boolean isValidExposeDateTime(Category child) {
        final LocalDateTime now = LocalDateTime.now();
        return !(now.isBefore(child.getCloseDate()) && now.isAfter(child.getOpenDate()));
    }

    private boolean isValidatedRelationship(Category parentCategory, Category c) {
        return !(parentCategory.getCategoryId() == c.getParentCategory().getCategoryId()) &&
                !(parentCategory.getCategoryLevel().isSubLevel(c.getCategoryLevel()));
    }
}
