package name.guolanren.endpoint;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

/**
 * @author guolanren
 */
@RestController
public class UserInfoEndpoint {

    @GetMapping("/me")
    public Principal me(Principal principal) {
        Set<String> scopes = ((OAuth2Authentication) principal).getOAuth2Request().getScope();
        return principal;
    }

}
