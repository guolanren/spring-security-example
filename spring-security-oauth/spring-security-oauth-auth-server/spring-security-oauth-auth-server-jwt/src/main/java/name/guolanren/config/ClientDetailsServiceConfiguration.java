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
                .authorizedGrantTypes("authorization_code", "refresh_code")
                .accessTokenValiditySeconds(60 * 60 * 12)
                .refreshTokenValiditySeconds(60 * 60 * 24 * 30)
                .redirectUris("http://localhost:8080/login/oauth2/code/auth")
                .resourceIds("user-info", "resource-id")
                .scopes("profile", "email", "phone")
                .autoApprove("true")
                .and()
            .withClient("resource")
                .secret("resource")
                .authorizedGrantTypes("client_credentials")
                .authorities("ROLE_RESOURCE")
                .accessTokenValiditySeconds(60 * 60 * 12);

        return builder.build();
    }

}
