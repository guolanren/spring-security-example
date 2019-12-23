package name.guolanren.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy;
import org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

/**
 * @author guolanren
 */
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpringSessionBackedSessionRegistry sessionRegistry;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/index").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .and()
            .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
            .sessionManagement()
                .invalidSessionUrl("/index")
                .invalidSessionStrategy(new SimpleRedirectInvalidSessionStrategy("/index"))
                .maximumSessions(1)
                    .sessionRegistry(sessionRegistry)
                    .expiredUrl("/index")
                    .expiredSessionStrategy(new SimpleRedirectSessionInformationExpiredStrategy("/index"))
                    .and()
                .and()
            .csrf().disable();
    }

}
