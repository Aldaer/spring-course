package beans.services;

import beans.daos.AbstractDAO;
import beans.daos.UserAccountDAO;
import beans.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userAccountServiceImpl")
public class UserAccountServiceImpl extends AbstractDAO implements UserAccountService {
    private final UserAccountDAO userAccountDAO;

    @Autowired
    public UserAccountServiceImpl(UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public double getBalance(User user) {
        return userAccountDAO.getBalance(user);
    }

    @Override
    @Transactional
    public void creditAccount(User user, double amount) {
        userAccountDAO.setBalance(user, userAccountDAO.getBalance(user) + amount);
    }

    @Override
    @Transactional
    public void debitAccount(User user, double amount) {
        double newBalance = userAccountDAO.getBalance(user) - amount;
        if (newBalance < 0) {
            throw new IllegalStateException("User " + user.getName() + " does not have enough funds");
        }
        userAccountDAO.setBalance(user, newBalance);
    }
}
