package br.com.yourwishismycommand.infra.security;

import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public static UserDetails adapt(User user) {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority(user.getRole().name()));
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getEmail().toString();
            }
        };
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionalUser = userRepository.findByEmail(new Email(username));
        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("%s was not found", username));
        }
        var user = optionalUser.get();
        return adapt(user);
    }
}
