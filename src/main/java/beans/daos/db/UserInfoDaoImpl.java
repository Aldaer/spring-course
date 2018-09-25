package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.UserDAO;
import beans.daos.UserInfoDAO;
import beans.models.User;
import beans.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class UserInfoDaoImpl extends AbstractDAO implements UserInfoDAO {
    private final UserDAO userDao;

    @Autowired
    public UserInfoDaoImpl(@Qualifier("userDAO") UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public UserInfo getUserInfo(User user) {
        return Optional.ofNullable(getCurrentSession().get(UserInfo.class, user.getId())).orElse(new UserInfo(user, ""));
    }

    @Override
    @Transactional
    public void registerUserInfo(UserInfo userInfo) {
        userInfo.setUser(userDao.create(userInfo.getUser()));
        getCurrentSession().update(userInfo);
    }

    @Override
    @Transactional
    public UserInfo findByEmail(String email) {
        User byEmail = userDao.getByEmail(email);
        UserInfo userInfo = getUserInfo(byEmail);
        return userInfo;
    }
}
