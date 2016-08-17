package commerce.app.service;

import commerce.app.dto.SearchContext;
import commerce.app.entity.Product;
import commerce.app.repository.ProductJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author chanwook
 */
@Service
public class SearchService {

    private final Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    ProductJpaRepository productRepository;

    public void searchByKeyword(SearchContext context) {
        List<Product> searchProduct;
        if (context.isValidatedKeyword()) {
            //TODO 여러 속성 검색을 더 지원...
            searchProduct = productRepository.findByProductNameLike("%" + context.getKeyword() + "%");
        } else {
            logger.debug("유효하지 않은 검색어로 검색 상품 결과가 없습니다.{검색어: []}");
            searchProduct = Collections.emptyList();
        }
        context.setSearchProduct(searchProduct);
    }
}
