package commerce.app.service;

import commerce.app.dto.SearchContext;
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

    public SearchContext searchByKeyword(String keyword) {
        final SearchContext context = new SearchContext();

        //TODO 여러 속성 검색을 더 지원...
        List<Product> searchProduct =
                productRepository.findByProductNameLike("%" + keyword + "%");
        context.setSearchProduct(searchProduct);

        return context;
    }
}
