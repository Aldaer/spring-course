package beans.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Column
    private String roles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return Optional.ofNullable(roles)
                .map(roles -> roles.split(","))
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public UserInfo(User user, String password) {
        this.user = user;
        this.password = password;
    }

    public UserInfo(User user, String password, String roles) {
        this(user, password);
        this.setRoles(roles);
    }

    public UserInfo() {
    }
}
