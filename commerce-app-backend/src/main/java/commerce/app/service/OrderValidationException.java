package commerce.app.service;

/**
 * @author chanwook
 */
public class OrderValidationException extends RuntimeException {
    public OrderValidationException(String message) {
        super(message);
    }
}
