package name.guolanren.config;

import name.guolanren.login.sms.SmsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author guolanren
 */
@Configuration
public class AuthenticationProviderConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider smsAuthenticationProvider() {
        return new SmsAuthenticationProvider(userDetailsService);
    }

}
