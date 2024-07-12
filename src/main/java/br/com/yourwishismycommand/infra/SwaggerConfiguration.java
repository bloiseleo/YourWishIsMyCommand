package br.com.yourwishismycommand.infra;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setName("Leonardo Bloise");
        contact.setEmail("leonardocsbloise@gmail.com");
        Info info = new Info()
                .title("Your Wish is My Command API")
                .version("0.0.1")
                .description("API of YWIMC that exposes endpoints to manage orders and clients")
                .contact(contact);
        return new OpenAPI()
                .info(info);
    }
}
