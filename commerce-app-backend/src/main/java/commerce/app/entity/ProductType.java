package commerce.app.entity;

/**
 * @author chanwook
 */
public enum ProductType {
    P("일반상품"), B("도서상품"), G("상품권");

    private final String description;

    ProductType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
