package br.com.yourwishismycommand.infra.controllers;

import br.com.yourwishismycommand.application.dtos.APIBaseResponse;
import br.com.yourwishismycommand.application.dtos.APIValuableResponse;
import br.com.yourwishismycommand.application.usecases.AuhtenticateUserUseCase;
import br.com.yourwishismycommand.application.usecases.RegisterUserUseCase;
import br.com.yourwishismycommand.domain.exceptions.InvalidCredentialsException;
import br.com.yourwishismycommand.infra.dtos.UserDTO;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static br.com.yourwishismycommand.infra.HttpResponseHelper.created;
@RestController
@RequestMapping("auth")
public class AuthController {
    private final RegisterUserUseCase registerUserUseCase;
    private final AuhtenticateUserUseCase auhtenticateUserUseCase;
    public AuthController(RegisterUserUseCase   registerUserUseCase, AuhtenticateUserUseCase auhtenticateUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.auhtenticateUserUseCase = auhtenticateUserUseCase;
    }
    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<APIBaseResponse> register(@RequestBody UserDTO createUserDTO) {
        var user = registerUserUseCase.registerUser(createUserDTO);
        return created(
                String.format("User of email %s created successfully", user.getEmail())
        );
    }
    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<APIValuableResponse<String>> login(@RequestBody UserDTO loginDTO) throws InvalidCredentialsException {
        var token = auhtenticateUserUseCase.authenticate(loginDTO);
        return ResponseEntity.ok(new APIValuableResponse<>(
                HttpStatus.OK.value(),
                "Successfully authenticated",
                token
        ));
    }
}
