package name.guolanren.authentication;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guolanren
 */
public class CaptchaWebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new CaptchaWebAuthenticationDetails(context);
    }
}
