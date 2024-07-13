package br.com.yourwishismycommand.infra.controllers;

import br.com.yourwishismycommand.application.dtos.APIBaseResponse;
import br.com.yourwishismycommand.application.usecases.RegisterUserUseCase;
import br.com.yourwishismycommand.infra.dtos.UserDTO;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static br.com.yourwishismycommand.infra.HttpResponseHelper.created;
@RestController
@RequestMapping("auth")
public class AuthController {
    private final RegisterUserUseCase registerUserUseCase;
    public AuthController(RegisterUserUseCase   registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }
    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<APIBaseResponse> register(@RequestBody UserDTO createUserDTO) {
        var user = registerUserUseCase.registerUser(createUserDTO);
        return created(
                String.format("User of email %s created successfully", user.getEmail())
        );
    }
}
