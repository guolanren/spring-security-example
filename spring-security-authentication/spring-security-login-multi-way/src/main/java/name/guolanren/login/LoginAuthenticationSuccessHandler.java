package name.guolanren.login;

import name.guolanren.model.LoginParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static name.guolanren.login.LoginAuthenticationProcessingFilter.LOGIN_PARAM;

/**
 * @author guolanren
 */
public class LoginAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginParam loginParam = obtainLoginParam(request);
        setDefaultTargetUrl(loginParam.getReturnTo());
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private LoginParam obtainLoginParam(HttpServletRequest request) throws IOException {
        return (LoginParam) request.getAttribute(LOGIN_PARAM);
    }
}
