package beans.daos;

import beans.models.User;
import beans.models.UserInfo;

public interface UserInfoDAO {
    UserInfo getUserInfo(User user);
    UserInfo findByEmail(String email);

    void registerUserInfo(UserInfo userInfo);
}
