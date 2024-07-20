package br.com.yourwishismycommand.infra.controllers;

import br.com.yourwishismycommand.application.dtos.APIBaseResponse;
import br.com.yourwishismycommand.application.dtos.APIValuableResponse;
import br.com.yourwishismycommand.application.dtos.outbound.OutboundProfileDTO;
import br.com.yourwishismycommand.application.usecases.CreateProfileUseCase;
import br.com.yourwishismycommand.application.usecases.ExtractAllProfilesUseCase;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.infra.dtos.ProfileDTO;
import br.com.yourwishismycommand.infra.dtos.outbound.OutboundProfileDTOImpl;
import br.com.yourwishismycommand.infra.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {
    private final CreateProfileUseCase createProfileUseCase;
    private final ExtractAllProfilesUseCase extractAllProfilesUseCase;
    public ProfileController(
            CreateProfileUseCase createProfileUseCase,
            ExtractAllProfilesUseCase extractAllProfilesUseCase) {
        this.createProfileUseCase = createProfileUseCase;
        this.extractAllProfilesUseCase = extractAllProfilesUseCase;
    }
    private User extractUserFromSecurityContext() {
        var authenticationToken = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        var details = (UserDetailsImpl) authenticationToken.getPrincipal();
        return details.getUser();
    }
    @PostMapping()
    public APIBaseResponse createClient(@RequestBody ProfileDTO profileDTO) {
        var user = extractUserFromSecurityContext();
        createProfileUseCase.createProfile(profileDTO, user);
        return new APIBaseResponse(
                HttpStatus.CREATED.value(),
                "Profile created successfully"
        );
    }
    @GetMapping
    public APIValuableResponse<List<OutboundProfileDTOImpl>> allProfiles() {
        var user = extractUserFromSecurityContext();
        var profiles = extractAllProfilesUseCase.profilesOf(user)
                .stream()
                .map(profile -> new OutboundProfileDTOImpl(
                        profile.getAddress(),
                        profile.getUserRole(),
                        profile.getTradingName()
                )).toList();
        return new APIValuableResponse<>(
                HttpStatus.OK.value(),
                String.format("All profiles of user %s", user.getName()),
                profiles
        );
    }
}
