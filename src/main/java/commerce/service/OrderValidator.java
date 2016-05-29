package commerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chanwook
 */
public interface OrderValidator {
    Logger LOGGER = LoggerFactory.getLogger(OrderValidator.class);

    void validate(ValidationSource validationSource);
}
