package name.guolanren.login;

import name.guolanren.exception.VerificationCodeException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author guolanren
 */
public class VerificationCodeFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("/login".equals(request.getRequestURI())) {
            verificationCode(request);
        }
        filterChain.doFilter(request, response);
    }

    private void verificationCode(HttpServletRequest request) {
        String requestCode = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String savedCode = (String) session.getAttribute("captcha");
        if (!StringUtils.isEmpty(savedCode)) {
            // 移除验证码，无论失败还是成功
            session.removeAttribute("captcha");
        }

        if (StringUtils.isEmpty(requestCode) || StringUtils.isEmpty(savedCode)
                || !requestCode.equalsIgnoreCase(savedCode)) {
            throw new VerificationCodeException("验证码无效");
        }
    }
}
