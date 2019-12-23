package name.guolanren.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

/**
 * @author guolanren
 */
@Configuration
public class UserDetailsServiceConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService inMemoryUserDetailsService() {
        User.UserBuilder users = User.builder().passwordEncoder(passwordEncoder()::encode);

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("guolanren").password("hahaha").roles("USER").build());
        manager.createUser(users.username("admin").password("admin").roles("ADMIN").build());
        return manager;
    }

    @Bean
    public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
        User.UserBuilder users = User.builder().passwordEncoder(passwordEncoder()::encode);

        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.createUser(users.username("guolanren").password("hahaha").roles("USER").build());
        manager.createUser(users.username("admin").password("admin").roles("ADMIN").build());
        return manager;
    }

}
