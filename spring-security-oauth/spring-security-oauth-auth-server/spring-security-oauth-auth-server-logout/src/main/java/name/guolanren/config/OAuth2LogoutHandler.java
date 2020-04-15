package name.guolanren.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guolanren
 */
public class OAuth2LogoutHandler implements LogoutHandler {

    private TokenStore tokenStore;

    public OAuth2LogoutHandler(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String refreshToken = request.getParameter("refreshToken");
        OAuth2RefreshToken refreshTokenEntity = tokenStore.readRefreshToken(refreshToken);

        // 移除 accessToken, refreshToken
        tokenStore.removeAccessTokenUsingRefreshToken(refreshTokenEntity);
        tokenStore.removeRefreshToken(refreshTokenEntity);
    }
}
