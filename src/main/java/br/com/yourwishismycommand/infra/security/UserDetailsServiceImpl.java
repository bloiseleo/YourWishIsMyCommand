package br.com.yourwishismycommand.infra.security;

import br.com.yourwishismycommand.application.services.RoleDecisionMakerService;
import br.com.yourwishismycommand.domain.entities.Email;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.entities.UserRole;
import br.com.yourwishismycommand.domain.repositories.ProfileRepository;
import br.com.yourwishismycommand.domain.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleDecisionMakerService roleDecisionMakerService;
    public UserDetailsServiceImpl(UserRepository userRepository, RoleDecisionMakerService roleDecisionMakerService) {
        this.userRepository = userRepository;
        this.roleDecisionMakerService = roleDecisionMakerService;
    }
    public static UserDetailsImpl adapt(User user, UserRole role) {
        return new UserDetailsImpl(user, role);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionalUser = userRepository.findByEmail(new Email(username));
        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("%s was not found", username));
        }
        var user = optionalUser.get();
        return adapt(user, roleDecisionMakerService.decide(user));
    }
}
