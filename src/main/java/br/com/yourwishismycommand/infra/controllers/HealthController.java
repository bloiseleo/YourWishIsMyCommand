package br.com.yourwishismycommand.infra.controllers;

import br.com.yourwishismycommand.application.dtos.APIValuableResponse;
import br.com.yourwishismycommand.infra.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("health")
public class HealthController {
    @GetMapping
    public ResponseEntity<APIValuableResponse<String>> ping() {
        return ResponseEntity.ok(new APIValuableResponse<>(
                HttpStatus.OK.value(),
                "OK!",
                "pong"
        ));
    }
    private UserDetailsImpl extractUserFromSecurityContext() {
        var authenticationToken = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) authenticationToken.getPrincipal();
    }
    @GetMapping("role")
    public APIValuableResponse<String> checkRole() {
        var details = extractUserFromSecurityContext();
        var role = details.getRole();
        return new APIValuableResponse<>(
                HttpStatus.OK.value(),
                "Checked user profile successfully",
                role.name()
        );
    }
}
