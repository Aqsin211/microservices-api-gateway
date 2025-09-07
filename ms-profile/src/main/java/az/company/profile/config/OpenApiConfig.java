package az.company.profile.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(new Server().url("http://localhost:8081")))
                .components(new Components())
                .info(new Info()
                        .title("Profile Service API")
                        .version("1.0.0")
                        .description("This service manages user profiles")
                        .contact(new Contact()
                                .name("Agshin")
                                .email("aqsinsteam@gmail.com")
                        )
                );
    }
}
