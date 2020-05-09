package name.guolanren.config;

import name.guolanren.login.LoginAuthenticationFailureHandler;
import name.guolanren.login.LoginAuthenticationProcessingFilter;
import name.guolanren.login.LoginAuthenticationSuccessHandler;
import name.guolanren.login.sms.SmsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;
import java.util.List;

/**
 * @author guolanren
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    List<AuthenticationProvider> authenticationProviders = Collections.emptyList();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginProcessingUrl("/login")
                .and()
            .addFilterAt(authenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
            .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/favicon.ico");
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        ProviderManager providerManager = (ProviderManager) super.authenticationManager();
        providerManager.getProviders().addAll(authenticationProviders);
        return providerManager;
    }

    private LoginAuthenticationProcessingFilter authenticationProcessingFilter() throws Exception {
        LoginAuthenticationProcessingFilter loginAuthenticationProcessingFilter = new LoginAuthenticationProcessingFilter();
        loginAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager());
        loginAuthenticationProcessingFilter.setAuthenticationSuccessHandler(new LoginAuthenticationSuccessHandler());
        loginAuthenticationProcessingFilter.setAuthenticationFailureHandler(new LoginAuthenticationFailureHandler());
        return loginAuthenticationProcessingFilter;
    }
}
