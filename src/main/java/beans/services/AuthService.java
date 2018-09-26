package beans.services;

import beans.daos.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

@Component
public class AuthService implements UserDetailsService {
    private final UserInfoDAO userInfoDAO;

    @Autowired
    public AuthService(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return ofNullable(email)
                .map(userInfoDAO::findByEmail)
                .map(userInfo -> new UserDetails() {
                    Set<String> roles = Stream.concat(userInfo.getRoles().stream().map(s -> "ROLE_" + s),
                            Stream.of("ROLE_REGISTERED_USER"))
                            .collect(Collectors.toSet());

                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return roles.stream().map(SimpleGrantedAuthority::new)
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
                }).orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
