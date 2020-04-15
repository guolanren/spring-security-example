package name.guolanren.login;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.CharStreams;
import name.guolanren.model.LoginParam;
import name.guolanren.login.password.PasswordAuthenticationToken;
import name.guolanren.login.sms.SmsAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.Assert;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author guolanren
 */
public class LoginAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public static final String LOGIN_PARAM = "LOGIN_PARAM";

    public static final String SMS = "sms";
    public static final String PASSWORD = "password";

    public LoginAuthenticationProcessingFilter() {
        super("/login");
        setContinueChainBeforeSuccessfulAuthentication(false);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        LoginParam loginParam = obtainLoginParam(request);
        String authType = loginParam.getAuthType();
        switch (authType) {
            case SMS: return doSmsAuth(loginParam);
            case PASSWORD: return doPasswordAuth(loginParam);
            default: throw new AuthenticationServiceException("不支持的登录方式: " + authType);
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            storeLoginParamInRequestAttribute(req);
            super.doFilter(req, res, chain);
        } finally {
            removeLoginParamFromRequestAttribute(req);
        }
    }

    private Authentication doPasswordAuth(LoginParam loginParam) {
        String phone = loginParam.getPhone() == null ? "" : loginParam.getPhone().trim();
        String password = loginParam.getPassword() == null ? "" : loginParam.getPassword();

        PasswordAuthenticationToken authRequest = new PasswordAuthenticationToken(phone, password);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private Authentication doSmsAuth(LoginParam loginParam) {
        String phone = loginParam.getPhone() == null ? "" : loginParam.getPhone().trim();
        String actualSms = loginParam.getSms() == null ? "" : loginParam.getSms();

        SmsAuthenticationToken authRequest = new SmsAuthenticationToken(phone, actualSms);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private LoginParam obtainLoginParam(HttpServletRequest request) throws IOException {
        return (LoginParam) request.getAttribute(LOGIN_PARAM);
    }

    private void storeLoginParamInRequestAttribute(ServletRequest request) throws IOException, IllegalArgumentException, JSONException {
        request.setCharacterEncoding("UTF-8");
        String requestBody = CharStreams.toString(request.getReader());

        LoginParam loginParam = JSONObject.parseObject(requestBody, LoginParam.class);

        request.setAttribute(LOGIN_PARAM, loginParam);
    }

    private void removeLoginParamFromRequestAttribute(ServletRequest request) {
        request.removeAttribute(LOGIN_PARAM);
    }
}
