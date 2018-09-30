package beans.models;

import javax.persistence.*;

@Entity
public class UserAccount {
    @Id
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @MapsId
    private User user;

    private double balance;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UserAccount(User user) {
        this.user = user;
    }
}
