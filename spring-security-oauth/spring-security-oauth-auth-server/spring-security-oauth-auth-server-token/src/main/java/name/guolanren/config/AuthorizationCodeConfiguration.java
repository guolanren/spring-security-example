package name.guolanren.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import javax.sql.DataSource;

/**
 * @author guolanren
 */
@Configuration
public class AuthorizationCodeConfiguration {

    private static final String DEFAULT_SELECT_STATEMENT = "select code, authentication from oauth_authorization_code where code = ?";
    private static final String DEFAULT_INSERT_STATEMENT = "insert into oauth_authorization_code (code, authentication) values (?, ?)";
    private static final String DEFAULT_DELETE_STATEMENT = "delete from oauth_authorization_code where code = ?";

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        JdbcAuthorizationCodeServices authorizationCodeServices = new JdbcAuthorizationCodeServices(dataSource);

        authorizationCodeServices.setSelectAuthenticationSql(DEFAULT_SELECT_STATEMENT);
        authorizationCodeServices.setInsertAuthenticationSql(DEFAULT_INSERT_STATEMENT);
        authorizationCodeServices.setDeleteAuthenticationSql(DEFAULT_DELETE_STATEMENT);

        return authorizationCodeServices;
    }

}
