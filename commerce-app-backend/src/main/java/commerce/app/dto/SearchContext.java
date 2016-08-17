package commerce.app.dto;

import commerce.app.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author chanwook
 */
@Getter
@Setter
public class SearchContext implements Serializable {

    private final String keyword;

    private Object searchCriteria;

    private List<Product> searchProduct;

    public SearchContext(String keyword) {
        this.keyword = keyword;
    }

    public boolean isValidatedKeyword() {
        return StringUtils.hasText(keyword);
    }
}
