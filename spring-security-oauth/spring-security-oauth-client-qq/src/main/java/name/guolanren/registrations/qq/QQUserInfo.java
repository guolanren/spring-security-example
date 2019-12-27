package name.guolanren.registrations.qq;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guolanren
 */
public class QQUserInfo implements OAuth2User {

    private List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");

    private Map<String, Object> attributes;

    private String nickname;

    @JsonProperty("figureurl")
    private String figureUrl30;

    @JsonProperty("figureurl_1")
    private String figureUrl50;

    @JsonProperty("figureurl_2")
    private String figureUrl100;

    @JsonProperty("figureurl_qq_1")
    private String qqFigureUrl40;

    @JsonProperty("figureurl_qq_2")
    private String qqFigureUrl100;

    private String gender;

    private String openId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (attributes == null) {
            attributes = new HashMap<>(8);
            attributes.put("nickname", getNickname());
            attributes.put("figureUrl30", getFigureUrl30());
            attributes.put("figureUrl50", getFigureUrl50());
            attributes.put("figureUrl100", getFigureUrl100());
            attributes.put("qqFigureUrl40", getQqFigureUrl40());
            attributes.put("qqFigureUrl100", getFigureUrl100());
            attributes.put("gender", getGender());
            attributes.put("openId", getOpenId());
        }
        return attributes;
    }

    @Override
    public String getName() {
        return this.nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFigureUrl30() {
        return figureUrl30;
    }

    public void setFigureUrl30(String figureUrl30) {
        this.figureUrl30 = figureUrl30;
    }

    public String getFigureUrl50() {
        return figureUrl50;
    }

    public void setFigureUrl50(String figureUrl50) {
        this.figureUrl50 = figureUrl50;
    }

    public String getFigureUrl100() {
        return figureUrl100;
    }

    public void setFigureUrl100(String figureUrl100) {
        this.figureUrl100 = figureUrl100;
    }

    public String getQqFigureUrl40() {
        return qqFigureUrl40;
    }

    public void setQqFigureUrl40(String qqFigureUrl40) {
        this.qqFigureUrl40 = qqFigureUrl40;
    }

    public String getQqFigureUrl100() {
        return qqFigureUrl100;
    }

    public void setQqFigureUrl100(String qqFigureUrl100) {
        this.qqFigureUrl100 = qqFigureUrl100;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
