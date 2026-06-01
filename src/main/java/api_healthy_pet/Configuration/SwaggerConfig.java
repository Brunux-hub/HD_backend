package api_healthy_pet.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "API HealthyPets",
                description = "API REST para la gestión de servicios veterinarios, mascotas, propietarios y citas médicas.",
                version = "1.0.0",
                license = @License(
                        name = "Uso académico"
                )
        )
)
public class SwaggerConfig {

}
