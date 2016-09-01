package commerce.app.dto;

import commerce.app.entity.CategoryLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 카테고리 매장 화면에서 사용하는 DTO
 *
 * @author chanwook
 */
@Getter
@Setter
public class CategoryShopDto implements Serializable {

    private long categoryId;

    private CategoryLevel categoryLevel;

    private String displayName;

}
