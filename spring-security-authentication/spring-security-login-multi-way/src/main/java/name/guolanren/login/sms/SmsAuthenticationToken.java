package name.guolanren.login.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author guolanren
 */
public class SmsAuthenticationToken extends AbstractAuthenticationToken {

    private Object principal;
    private Object credentials;
    private String phone;
    private String sms;

    public SmsAuthenticationToken(String phone, String sms) {
        super(null);
        this.phone = phone;
        this.sms = sms;
        setAuthenticated(false);
    }

    public SmsAuthenticationToken(Object principal, Object credentials,
                                  Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getPhone() {
        return phone;
    }

    public String getSms() {
        return sms;
    }

}
