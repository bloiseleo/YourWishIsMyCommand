package br.com.yourwishismycommand.infra.security;

import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private User user;
    private UserRole role;
    public UserDetailsImpl(User user, UserRole role) {
        this.user = user;
        this.role = role;
    }
    public UserRole getRole() {
        if(role == null) {
            return UserRole.GUEST;
        }
        return role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail().toString();
    }
    public User getUser() {
        return user;
    }
}
