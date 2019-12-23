package name.guolanren.service;

import name.guolanren.dao.RememberMeTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author guolanren
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RememberMeTokenService implements PersistentTokenRepository {

    @Autowired
    private RememberMeTokenMapper rememberMeTokenMapper;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        rememberMeTokenMapper.insert(token);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        PersistentRememberMeToken persistentRememberMeToken = new PersistentRememberMeToken(null, series, tokenValue, lastUsed);
        rememberMeTokenMapper.update(persistentRememberMeToken);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return rememberMeTokenMapper.getBySeries(seriesId);
    }

    @Override
    public void removeUserTokens(String username) {
        rememberMeTokenMapper.deleteByUsername(username);
    }

}
