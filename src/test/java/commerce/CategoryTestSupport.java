package commerce;

import commerce.entity.Category;
import commerce.entity.CategoryLevel;

/**
 * @author chanwook
 */
public class CategoryTestSupport {
    public static Category createBLevel(int categoryId, String displayName) {
        final Category c = new Category(categoryId, CategoryLevel.B);
        c.setDisplayName(displayName);
        return c;
    }
}
