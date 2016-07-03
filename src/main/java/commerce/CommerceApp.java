package commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chanwook
 */
@RestController
@SpringBootConfiguration
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