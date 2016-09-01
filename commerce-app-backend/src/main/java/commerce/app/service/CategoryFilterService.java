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
    public List<ChildCategoryDto> filteringValidChildCategory(Category parentCategory, List<Category> targetChildCategory) {
        final List<ChildCategoryDto> validList = new ArrayList<>();
        for (Category c : targetChildCategory) {
            // 혹시 모를 잘못된 관계를 맺고 있지 않은지 점검
            if (!(parentCategory.getCategoryId() == c.getParentCategory().getCategoryId()) &&
                    !(parentCategory.getCategoryLevel().isSubLevel(c.getCategoryLevel()))) {
                logger.info("카테고리 관계가 부적절해 하위 카테고리 정보로 노출하지 않습니다. " +
                        "관련 정보를 확인해보세요! (parentCategory.getCategoryId() ->" + c.getCategoryId() + ")");
                continue;
            }

            //TODO 상품 없으면 노출하지 않음??

            // 노출 기간이 유효한지 확인
            final LocalDateTime now = LocalDateTime.now();
            if (!(now.isBefore(c.getCloseDate()) && now.isAfter(c.getOpenDate()))) {
                logger.info("노출 시간이 해당하지 않는 카테고리입니다!(현재시간 : {}, 카테고리 노출 시작 시간: {}, 카테고리 노출 종료시간: {}", now, c.getOpenDate(), c.getCloseDate());
                continue;
            }
            validList.add(new ChildCategoryDto(c.getCategoryId(), c.getDisplayName()));
        }
        return validList;
    }
}
