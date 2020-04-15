package name.guolanren.model;

import java.io.Serializable;

/**
 * @author guolanren
 */
public class LoginParam implements Serializable {

    private static final long serialVersionUID = -4987166591569088206L;

    private String phone;
    private String password;
    private String sms;
    private String authType;
    private String returnTo;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getReturnTo() {
        return returnTo;
    }

    public void setReturnTo(String returnTo) {
        this.returnTo = returnTo;
    }
}
