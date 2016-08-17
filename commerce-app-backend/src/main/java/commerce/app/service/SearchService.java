package commerce.app.service;

import commerce.app.dto.SearchContextMap;
import commerce.app.entity.Product;
import commerce.app.repository.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chanwook
 */
@Service
public class SearchService {
    @Autowired
    ProductJpaRepository productRepository;

    public SearchContextMap searchByKeyword(String keyword) {
        final SearchContextMap context = new SearchContextMap();

        //TODO 여러 속성 검색을 더 지원...
        List<Product> searchProduct =
                productRepository.findByProductNameLike("%" + keyword + "%");
        context.setSearchProduct(searchProduct);

        return context;
    }
}
