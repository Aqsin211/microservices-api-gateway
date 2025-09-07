package az.company.feedback.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(new Server().url("http://localhost:8082")))
                .components(new Components())
                .info(new Info()
                        .title("Feedback Service API")
                        .version("1.0.0")
                        .description("This service handles user feedback submission and retrieval")
                        .contact(new Contact()
                                .name("Agshin")
                                .email("aqsinsteam@gmail.com")
                        )
                );
    }
}
