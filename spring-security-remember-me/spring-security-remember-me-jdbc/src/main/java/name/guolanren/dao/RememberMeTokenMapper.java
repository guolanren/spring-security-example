package name.guolanren.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

/**
 * @author guolanren
 */
public interface RememberMeTokenMapper {

    void insert(PersistentRememberMeToken token);

    void update(PersistentRememberMeToken token);

    PersistentRememberMeToken getBySeries(@Param("series") String series);

    void deleteByUsername(@Param("username") String username);

}
