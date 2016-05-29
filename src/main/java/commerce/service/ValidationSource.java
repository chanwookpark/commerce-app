package commerce.service;

import java.util.HashMap;

/**
 * @author chanwook
 */
public class ValidationSource extends HashMap<String, Object> {
    public ValidationSource add(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
