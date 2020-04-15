package name.guolanren.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author guolanren
 */
@Configuration
public class UserDetailsServiceConfiguration {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsService inMemoryUserDetailsService() {
        User.UserBuilder users = User.builder().passwordEncoder(passwordEncoder::encode);

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("18812345678").password("hahaha").roles("USER").build());
        return manager;
    }

}
