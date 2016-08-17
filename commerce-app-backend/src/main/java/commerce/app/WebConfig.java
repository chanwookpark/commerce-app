package commerce.app;

import commerce.app.web.CommonModelAttributesHandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;

/**
 * @author chanwook
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Autowired
    Environment env;

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        String staticHost = env.getProperty("static.host");
        String templateSuffix = ".html";

        if (!staticHost.endsWith("/")) {
            staticHost += "/";
        }

        logger.info("[Template URL]{}~{}", staticHost, templateSuffix);

        //FIXME 프론트엔드와 함께 사용할 수 있도록 바꾸자..
        registry.scriptTemplate().prefix(staticHost).suffix(templateSuffix);
    }

    @Bean
    public ScriptTemplateConfigurer mustacheTemplate() {
        final ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
        configurer.setEngineName("nashorn");
        configurer.setScripts("public/mustache-render.js", "public/polyfill.js", "/META-INF/resources/webjars/mustache/2.2.1/mustache.min.js");
        configurer.setRenderObject("Mustache");
        configurer.setRenderFunction("render");
        return configurer;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonModelAttributesHandlerInterceptor(env));
    }
}
