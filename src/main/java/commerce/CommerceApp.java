package commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chanwook
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class CommerceApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CommerceApp.class, args);
    }

    @RequestMapping("/")
    String index() {
        return "Hello~";
    }

}