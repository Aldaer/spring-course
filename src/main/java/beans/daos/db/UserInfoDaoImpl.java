package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.UserDAO;
import beans.daos.UserInfoDAO;
import beans.models.User;
import beans.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Optional.ofNullable;

@Repository
public class UserInfoDaoImpl extends AbstractDAO implements UserInfoDAO {
    private final UserDAO userDao;
    private final PasswordEncoder pwdEncoder;

    @Autowired
    public UserInfoDaoImpl(@Qualifier("userDAO") UserDAO userDao, PasswordEncoder pwdEncoder) {
        this.userDao = userDao;
        this.pwdEncoder = pwdEncoder;
    }

    @Override
    @Transactional
    public UserInfo getUserInfo(User user) {
        return getCurrentSession().get(UserInfo.class, user.getId());
    }

    @Override
    @Transactional
    public void registerUserInfo(UserInfo userInfo) {
        userInfo.setUser(userDao.create(userInfo.getUser()));
        userInfo.setPassword(pwdEncoder.encode(userInfo.getPassword()));
        getCurrentSession().save(userInfo);
    }

    @Override
    @Transactional
    public UserInfo findByEmail(String email) {
        return ofNullable(email)
                .map(userDao::getByEmail)
                .map(this::getUserInfo)
                .orElse(null);
    }
}
