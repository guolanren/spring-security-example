package name.guolanren.login.sms;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author guolanren
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userService;
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    public SmsAuthenticationProvider(UserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken) authentication;

        String phone = authenticationToken.getPhone();
        String actualSms = authenticationToken.getSms();

        // 获取期待手机验证码
        // String expectedSms = smsStore.getSms(phone);
        String expectedSms = "123456";

        // 验证手机验证码
        if (! actualSms.equals(expectedSms)) {
            throw new BadCredentialsException("手机验证码错误");
        }

        // 手机号未注册则自动注册
        UserDetails userDetails = userService.loadUserByUsername(phone);
        if (userDetails == null) {
            throw new UsernameNotFoundException("手机号未注册...");
        }

        // 验证成功后删除验证码
        // smsStore.removeSms(phone);

        return createSuccessAuthentication(userDetails, authentication, userDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (SmsAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private Authentication createSuccessAuthentication(Object principal,
                                                         Authentication authentication, UserDetails user) {
        SmsAuthenticationToken result = new SmsAuthenticationToken(
                principal, authentication.getCredentials(),
                authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());

        return result;
    }
}
