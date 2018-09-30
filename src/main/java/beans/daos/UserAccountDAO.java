package beans.daos;

import beans.models.User;

public interface UserAccountDAO {
    double getBalance(User user);

    void setBalance(User user, double balance);
}
