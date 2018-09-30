package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.UserAccountDAO;
import beans.models.User;
import beans.models.UserAccount;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository("userAccountDAO")
public class UserAccountDAOImpl extends AbstractDAO implements UserAccountDAO {

    @Override
    @Transactional(readOnly = true)
    public double getBalance(User user) {
        return Optional.ofNullable(getCurrentSession().get(UserAccount.class, user.getId()))
                .map(UserAccount::getBalance).orElse(0d);
    }

    @Override
    @Transactional
    public void setBalance(User user, double balance) {
        Session currentSession = getCurrentSession();
        UserAccount userAccount = Optional.ofNullable(currentSession.get(UserAccount.class, user.getId()))
                .orElse(new UserAccount(user));
        userAccount.setBalance(balance);
        currentSession.saveOrUpdate(userAccount);
    }
}
