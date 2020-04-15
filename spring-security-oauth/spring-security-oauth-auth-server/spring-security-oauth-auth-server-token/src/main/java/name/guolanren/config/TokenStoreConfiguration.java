package name.guolanren.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @author guolanren
 */
@Configuration
public class TokenStoreConfiguration {

    private static final String AUTH_AUTHORIZATION_CODE_REDIS_KEY_PREFIX = "auth:authorization:code:";

    @Bean
    public RedisTokenStore tokenService(RedisConnectionFactory redisConnectionFactory) {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        // 设置 token 的 redis key 前缀
        redisTokenStore.setPrefix(AUTH_AUTHORIZATION_CODE_REDIS_KEY_PREFIX);
        return redisTokenStore;
    }

}
