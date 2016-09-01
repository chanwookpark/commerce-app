package commerce.app.entity;

/**
 * @author chanwook
 */
public enum CategoryLevel {
    A(1), B(2), C(3), D(4);

    private final int point;

    CategoryLevel(int point) {
        this.point = point;
    }

    public boolean isSubLevel(CategoryLevel categoryLevel) {
        if (this.point + 1 == categoryLevel.point) {
            return true;
        }
        return false;
    }
}
