package name.guolanren.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author guolanren
 */
@RestController
public class HiController {

    @GetMapping("/hi")
    public String hi(Principal principal) {
        return "hi " + principal.getName();
    }

}
