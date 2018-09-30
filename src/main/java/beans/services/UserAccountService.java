package beans.services;

import beans.models.User;

public interface UserAccountService {
    double getBalance(User user);

    void creditAccount(User user, double amount);

    void debitAccount(User user, double amount);
}
