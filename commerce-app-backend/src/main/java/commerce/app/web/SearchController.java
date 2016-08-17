package commerce.app.web;

import commerce.app.dto.SearchContext;
import commerce.app.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author chanwook
 */
@Controller
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    SearchService searchService;

    /**
     * 검색 결과 페이지
     *
     * @param model
     */
    @RequestMapping("/view/search")
    public String searchPage(ModelMap model) {
        return "search";
    }

    @RequestMapping("/view/search-webpack")
    public String searchPageWithWebPack(ModelMap model) {
        return "search-webpack";
    }

    @RequestMapping("/api/search")
    @ResponseBody
    public SearchContext search(@RequestParam(value = "keyword", required = true) String keyword) throws UnsupportedEncodingException {
        String decodedKeyword = URLDecoder.decode(keyword, "UTF-8");

        logger.debug("[검색어] 원본 파라미터: [], 디코딩 파라미터: []", keyword, decodedKeyword);

        SearchContext context = new SearchContext(decodedKeyword);
        searchService.searchByKeyword(context);

        return context;
    }
}
