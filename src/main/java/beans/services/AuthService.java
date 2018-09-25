package beans.services;

import beans.daos.UserInfoDAO;
import beans.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AuthService implements UserDetailsService {
    private final UserInfoDAO userInfoDAO;

    private final PasswordEncoder pwdEncoder;

    @Autowired
    public AuthService(UserInfoDAO userInfoDAO,
                       @Qualifier("pwdEncoder") PasswordEncoder pwdEncoder) {
        this.userInfoDAO = userInfoDAO;
        this.pwdEncoder = pwdEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoDAO.findByEmail(email);

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Stream.of("ROLE_REGISTERED_USER")
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            }

            @Override
            public String getPassword() {
                return userInfo.getPassword();
            }

            @Override
            public String getUsername() {
                return email;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}
