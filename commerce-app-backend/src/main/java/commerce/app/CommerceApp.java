package commerce.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chanwook
 */
@RestController
@SpringBootApplication
public class CommerceApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CommerceApp.class, args);
    }

    @RequestMapping("/")
    String index() {
        return "Hello~";
    }

}