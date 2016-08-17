package commerce.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chanwook
 */
@Controller
public class MainPageController {

    @RequestMapping("/main")
    public void main(ModelMap model) {
    }
}
