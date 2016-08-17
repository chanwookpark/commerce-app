package commerce.app.dto;

import commerce.app.entity.Product;

import java.io.Serializable;
import java.util.List;

/**
 * @author chanwook
 */
public class SearchContextMap implements Serializable {
    private Object searchCriteria;
    private List<Product> searchProduct;

    public Object getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(Object searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public List<Product> getSearchProduct() {
        return searchProduct;
    }

    public void setSearchProduct(List<Product> searchProduct) {
        this.searchProduct = searchProduct;
    }
}
