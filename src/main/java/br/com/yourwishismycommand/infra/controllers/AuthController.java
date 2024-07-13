package br.com.yourwishismycommand.infra.controllers;

import br.com.yourwishismycommand.application.usecases.RegisterUserUseCase;
import br.com.yourwishismycommand.infra.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private RegisterUserUseCase registerUserUseCase;
    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody UserDTO createUserDTO) {
        var user = registerUserUseCase.registerUser(createUserDTO);
        return "olá";
    }
}
