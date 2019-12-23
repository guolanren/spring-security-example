package name.guolanren.authentication;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author guolanren
 */
public class CaptchaWebAuthenticationDetails extends WebAuthenticationDetails {

    private final boolean isVerificationCodeRight;

    public CaptchaWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        String requestCode = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String savedCode = (String) session.getAttribute("captcha");
        if (!StringUtils.isEmpty(savedCode)) {
            // 移除验证码，无论失败还是成功
            session.removeAttribute("captcha");
        }

        isVerificationCodeRight = ! (StringUtils.isEmpty(requestCode) || StringUtils.isEmpty(savedCode)
                || !requestCode.equalsIgnoreCase(savedCode));
    }

    public boolean isVerificationCodeRight() {
        return isVerificationCodeRight;
    }
}
