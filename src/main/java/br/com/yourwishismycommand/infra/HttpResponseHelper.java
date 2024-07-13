package br.com.yourwishismycommand.infra;

import br.com.yourwishismycommand.application.dtos.APIBaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponseHelper {
    public static ResponseEntity<APIBaseResponse> created(String message) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    new APIBaseResponse(
                            HttpStatus.CREATED.value(),
                            message
                    )
                );
    }
}
