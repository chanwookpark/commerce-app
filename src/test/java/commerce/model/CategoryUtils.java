package commerce.model;

/**
 * @author chanwook
 */
public class CategoryUtils {
    public static Category createBLevel(int categoryId, String displayName) {
        final Category c = new Category(categoryId, CategoryLevel.B);
        c.setDisplayName(displayName);
        return c;
    }
}
