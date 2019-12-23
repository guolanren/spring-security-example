package name.guolanren.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guolanren
 */
@RestController
public class HiController {

    @GetMapping("/hi")
    public String hi() {
        return "hi";
    }

}
