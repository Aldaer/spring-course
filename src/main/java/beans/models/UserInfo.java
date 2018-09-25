package beans.models;

import javax.persistence.*;

@Entity
public class UserInfo {
    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id")
    @MapsId
    private User user;

    @Column
    private String password;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.id = user.getId();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo(User user, String password) {
        this.id = user.getId();
        this.user = user;
        this.password = password;
    }

    public UserInfo() {
    }
}
