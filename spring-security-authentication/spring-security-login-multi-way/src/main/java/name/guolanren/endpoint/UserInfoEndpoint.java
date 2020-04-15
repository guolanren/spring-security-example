package name.guolanren.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author guolanren
 */
@RestController
public class UserInfoEndpoint {

    @GetMapping("/me")
    public Principal me(Principal principal) {
        return principal;
    }

}
