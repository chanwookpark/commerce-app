package commerce.app.web;

import commerce.app.dto.SearchContextMap;
import commerce.app.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chanwook
 */
@Controller
public class SearchPageController {

    @Autowired
    SearchService searchService;

    /**
     * 검색 결과 및 재검색 페이지
     *
     * @param model
     */
    @RequestMapping("/search")
    public void search(ModelMap model,
                       @RequestParam(value = "keyword", required = true) String keyword) {

        SearchContextMap map = searchService.searchByKeyword(keyword);

        model.put("searchKeyword", keyword);
//        model.put("searchCriteria", map.getSearchCriteria());
        model.put("searchProduct", map.getSearchProduct());
    }
}
