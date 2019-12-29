package name.guolanren.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetailsService;

/**
 * @author guolanren
 */
@Configuration
public class ClientDetailsServiceConfiguration {

    @Bean
    public ClientDetailsService inMemoryClientDetailsService() throws Exception {
        InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();

        builder
            .withClient("client1")
                .secret("client1")
                .authorizedGrantTypes("authorization_code", "implicit")
                .accessTokenValiditySeconds(60 * 60 * 12)
                .redirectUris("http://localhost:8080/login/oauth2/code/auth")
                .authorities("ROLE_CLIENT")
                .scopes("profile", "email", "phone")
                .autoApprove("profile")
                .and()
            .withClient("client2")
                .secret("client2")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .accessTokenValiditySeconds(60 * 60 * 12)
                .refreshTokenValiditySeconds(60 * 60 * 24 * 30)
                .redirectUris("http://localhost:8080/login/oauth2/code/auth")
                .scopes("profile", "email", "phone")
                .autoApprove("true");

        return builder.build();
    }

}
