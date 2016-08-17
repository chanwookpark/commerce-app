package commerce.app.web;

import commerce.app.dto.SearchContext;
import commerce.app.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chanwook
 */
@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    /**
     * 검색 결과 페이지
     *
     * @param model
     */
    @RequestMapping("/view/search")
    public String searchPage(ModelMap model) {
        return "/search";
    }

    @RequestMapping("/view/search-webpack")
    public String searchPageWithWebPack(ModelMap model) {
        return "/search-webpack";
    }


    @RequestMapping("/api/search")
    @ResponseBody
    public SearchContext search(ModelMap model,
                                @RequestParam(value = "keyword", required = true) String keyword) {

        SearchContext context = searchService.searchByKeyword(keyword);

        model.put("searchKeyword", keyword);
//        model.put("searchCriteria", context.getSearchCriteria());
        model.put("searchProduct", context.getSearchProduct());
        return context;
    }
}
