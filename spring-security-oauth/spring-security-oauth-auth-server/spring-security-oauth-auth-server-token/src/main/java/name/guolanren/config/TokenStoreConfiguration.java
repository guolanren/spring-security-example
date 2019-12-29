package name.guolanren.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author guolanren
 */
@Configuration
public class TokenStoreConfiguration {

    private static final String DEFAULT_ACCESS_TOKEN_INSERT_STATEMENT = "insert into oauth_access_token (token_id, token, authentication_id, user_name, client_id, authentication, refresh_token) values (?, ?, ?, ?, ?, ?, ?)";

    private static final String DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT = "select token_id, token from oauth_access_token where token_id = ?";

    private static final String DEFAULT_ACCESS_TOKEN_AUTHENTICATION_SELECT_STATEMENT = "select token_id, authentication from oauth_access_token where token_id = ?";

    private static final String DEFAULT_ACCESS_TOKEN_FROM_AUTHENTICATION_SELECT_STATEMENT = "select token_id, token from oauth_access_token where authentication_id = ?";

    private static final String DEFAULT_ACCESS_TOKENS_FROM_USERNAME_AND_CLIENT_SELECT_STATEMENT = "select token_id, token from oauth_access_token where user_name = ? and client_id = ?";

    private static final String DEFAULT_ACCESS_TOKENS_FROM_USERNAME_SELECT_STATEMENT = "select token_id, token from oauth_access_token where user_name = ?";

    private static final String DEFAULT_ACCESS_TOKENS_FROM_CLIENTID_SELECT_STATEMENT = "select token_id, token from oauth_access_token where client_id = ?";

    private static final String DEFAULT_ACCESS_TOKEN_DELETE_STATEMENT = "delete from oauth_access_token where token_id = ?";

    private static final String DEFAULT_ACCESS_TOKEN_DELETE_FROM_REFRESH_TOKEN_STATEMENT = "delete from oauth_access_token where refresh_token = ?";

    private static final String DEFAULT_REFRESH_TOKEN_INSERT_STATEMENT = "insert into oauth_refresh_token (token_id, token, authentication) values (?, ?, ?)";

    private static final String DEFAULT_REFRESH_TOKEN_SELECT_STATEMENT = "select token_id, token from oauth_refresh_token where token_id = ?";

    private static final String DEFAULT_REFRESH_TOKEN_AUTHENTICATION_SELECT_STATEMENT = "select token_id, authentication from oauth_refresh_token where token_id = ?";

    private static final String DEFAULT_REFRESH_TOKEN_DELETE_STATEMENT = "delete from oauth_refresh_token where token_id = ?";

    @Bean
    public TokenStore jdbcTokenStore(DataSource dataSource) {
        JdbcTokenStore tokenStore = new JdbcTokenStore(dataSource);

        tokenStore.setInsertAccessTokenSql(DEFAULT_ACCESS_TOKEN_INSERT_STATEMENT);
        tokenStore.setSelectAccessTokenSql(DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT);
        tokenStore.setSelectAccessTokenAuthenticationSql(DEFAULT_ACCESS_TOKEN_AUTHENTICATION_SELECT_STATEMENT);
        tokenStore.setSelectAccessTokenFromAuthenticationSql(DEFAULT_ACCESS_TOKEN_FROM_AUTHENTICATION_SELECT_STATEMENT);
        tokenStore.setSelectAccessTokensFromUserNameAndClientIdSql(DEFAULT_ACCESS_TOKENS_FROM_USERNAME_AND_CLIENT_SELECT_STATEMENT);
        tokenStore.setSelectAccessTokensFromUserNameSql(DEFAULT_ACCESS_TOKENS_FROM_USERNAME_SELECT_STATEMENT);
        tokenStore.setSelectAccessTokensFromClientIdSql(DEFAULT_ACCESS_TOKENS_FROM_CLIENTID_SELECT_STATEMENT);
        tokenStore.setDeleteAccessTokenSql(DEFAULT_ACCESS_TOKEN_DELETE_STATEMENT);
        tokenStore.setDeleteAccessTokenFromRefreshTokenSql(DEFAULT_ACCESS_TOKEN_DELETE_FROM_REFRESH_TOKEN_STATEMENT);
        tokenStore.setInsertRefreshTokenSql(DEFAULT_REFRESH_TOKEN_INSERT_STATEMENT);
        tokenStore.setSelectRefreshTokenSql(DEFAULT_REFRESH_TOKEN_SELECT_STATEMENT);
        tokenStore.setSelectRefreshTokenAuthenticationSql(DEFAULT_REFRESH_TOKEN_AUTHENTICATION_SELECT_STATEMENT);
        tokenStore.setDeleteRefreshTokenSql(DEFAULT_REFRESH_TOKEN_DELETE_STATEMENT);

        return tokenStore;
    }

}
