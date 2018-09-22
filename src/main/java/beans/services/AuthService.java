package beans.services;

import beans.daos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class AuthService implements UserDetailsService {
    private final UserDAO userDAO;
    private final PasswordEncoder pwdEncoder;

    @Autowired
    public AuthService(@Qualifier("userDAO") UserDAO userDAO, @Qualifier("pwdEncoder") PasswordEncoder pwdEncoder) {
        this.userDAO = userDAO;
        this.pwdEncoder = pwdEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

//        User user = userDAO.getByEmail(email);

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singleton((GrantedAuthority) () -> "ROLE_REGISTERED_USER");
            }

            @Override
            public String getPassword() {
                return pwdEncoder.encode("123");
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
