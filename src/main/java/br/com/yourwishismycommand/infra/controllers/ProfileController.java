package br.com.yourwishismycommand.infra.controllers;

import br.com.yourwishismycommand.application.dtos.APIBaseResponse;
import br.com.yourwishismycommand.application.services.JwtManagerService;
import br.com.yourwishismycommand.application.usecases.CreateProfileUseCase;
import br.com.yourwishismycommand.domain.entities.User;
import br.com.yourwishismycommand.domain.exceptions.InvalidCredentialsException;
import br.com.yourwishismycommand.infra.dtos.ProfileDTO;
import br.com.yourwishismycommand.infra.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile")
public class ProfileController {
    private final CreateProfileUseCase createProfileUseCase;
    private final JwtManagerService jwtManagerService;
    public ProfileController(CreateProfileUseCase createProfileUseCase, JwtManagerService jwtManagerService) {
        this.createProfileUseCase = createProfileUseCase;
        this.jwtManagerService = jwtManagerService;
    }
    private User extractUserFromHeader(String authorization) throws InvalidCredentialsException {
        if(authorization == null) {
            throw new InvalidCredentialsException();
        }
        if(authorization.isEmpty()) {
            throw new InvalidCredentialsException();
        }
        var token = authorization.split("Bearer ");
        if(token.length <= 1) {
            throw new InvalidCredentialsException();
        }
        return jwtManagerService.decodeJwt(token[1]);
    }
    @PostMapping()
    public APIBaseResponse createClient(@RequestBody ProfileDTO profileDTO) {
        var authenticationToken = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        var details = (UserDetailsImpl) authenticationToken.getPrincipal();
        var user = details.getUser();
        createProfileUseCase.createProfile(profileDTO, user);
        return new APIBaseResponse(
                HttpStatus.CREATED.value(),
                "Profile created successfully"
        );
    }
}
