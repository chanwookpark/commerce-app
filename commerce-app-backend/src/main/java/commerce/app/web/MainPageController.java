package commerce.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chanwook
 */
@Controller
public class MainPageController {

    @Autowired
    Environment env;

    @RequestMapping("/main")
    public String main(ModelMap model) {
        model.put("staticHost", env.getProperty("static.host"));//TODO 공통으로 ...
        return "main";
    }
}
