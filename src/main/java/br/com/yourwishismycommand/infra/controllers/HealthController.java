package br.com.yourwishismycommand.infra.controllers;

import br.com.yourwishismycommand.application.dtos.APIValuableResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
