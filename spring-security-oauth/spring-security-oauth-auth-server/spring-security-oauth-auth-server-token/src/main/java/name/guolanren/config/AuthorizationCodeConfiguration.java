package name.guolanren.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * @author guolanren
 */
@Configuration
public class AuthorizationCodeConfiguration {

    private static final String DEFAULT_SELECT_STATEMENT = "select code, authentication from oauth_authorization_code where code = ?";
    private static final String DEFAULT_INSERT_STATEMENT = "insert into oauth_authorization_code (code, authentication) values (?, ?)";
    private static final String DEFAULT_DELETE_STATEMENT = "delete from oauth_authorization_code where code = ?";

    @Bean("jdbcAuthorizationCodeServices")
    public AuthorizationCodeServices jdbcAuthorizationCodeServices(DataSource dataSource) {
        JdbcAuthorizationCodeServices authorizationCodeServices = new JdbcAuthorizationCodeServices(dataSource);

        authorizationCodeServices.setSelectAuthenticationSql(DEFAULT_SELECT_STATEMENT);
        authorizationCodeServices.setInsertAuthenticationSql(DEFAULT_INSERT_STATEMENT);
        authorizationCodeServices.setDeleteAuthenticationSql(DEFAULT_DELETE_STATEMENT);

        return authorizationCodeServices;
    }

    @Bean("redisAuthorizationCodeServices")
    public AuthorizationCodeServices redisAuthorizationCodeServices(RedisConnectionFactory redisConnectionFactory) {
        return new RedisAuthorizationCodeServices(redisConnectionFactory);
    }

    private class RedisAuthorizationCodeServices implements AuthorizationCodeServices {

        private static final String AUTH_AUTHORIZATION_CODE_REDIS_KEY_PREFIX = "auth:authorization:code:";

        private final RedisTemplate<String, OAuth2Authentication> redis;
        private RandomValueStringGenerator generator = new RandomValueStringGenerator();

        public RedisAuthorizationCodeServices(RedisConnectionFactory redisConnectionFactory) {
            RedisTemplate<String, OAuth2Authentication> redisTemplate = new RedisTemplate<>();
            redisTemplate.setKeySerializer(RedisSerializer.string());
            redisTemplate.setValueSerializer(RedisSerializer.java());
            redisTemplate.setConnectionFactory(redisConnectionFactory);
            this.redis = redisTemplate;
        }

        @Override
        public String createAuthorizationCode(OAuth2Authentication authentication) {
            String code = generator.generate();
            // 授权码 1 分钟内有效
            redis.boundValueOps(AUTH_AUTHORIZATION_CODE_REDIS_KEY_PREFIX + code)
                    .set(authentication, 1, TimeUnit.MINUTES);
            return code;
        }

        @Override
        public OAuth2Authentication consumeAuthorizationCode(String code) throws InvalidGrantException {
            String codeKey = AUTH_AUTHORIZATION_CODE_REDIS_KEY_PREFIX + code;
            try {
                return redis.boundValueOps(codeKey).get();
            } finally {
                // 授权码 1 次有效，用完即删，无论获取 token 成功失败
                redis.delete(codeKey);
            }
        }
    }
}
