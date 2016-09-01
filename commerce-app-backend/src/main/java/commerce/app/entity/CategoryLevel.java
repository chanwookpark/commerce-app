package commerce.app.entity;

/**
 * @author chanwook
 */
public enum CategoryLevel {
    A, B, C, D;

    public boolean isSubLevel(CategoryLevel categoryLevel) {
        //TODO 개선
        if (A.equals(this) && categoryLevel.equals(B) &&
                B.equals(this) && categoryLevel.equals(C) &&
                C.equals(this) && categoryLevel.equals(D)
                ) {
            return true;
        }
        return false;
    }
}
