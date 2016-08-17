package commerce.app.web;

import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chanwook
 */
public class CommonModelAttributesHandlerInterceptor extends HandlerInterceptorAdapter {

    private Environment env;

    public CommonModelAttributesHandlerInterceptor(Environment env) {
        this.env = env;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        final String staticHost = env.getProperty("static.host");
        modelAndView.addObject("staticHost", staticHost);
    }
}
