package api_healthy_pet.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI healthyPetsOpenApi() {
        String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info()
                        .title("HealthyPets API")
                        .description("API REST para la veterinaria HealthyPets")
                        .version("v1"))
                .tags(List.of(
                        new Tag().name("Auth").description("Autenticacion"),
                        new Tag().name("Usuarios").description("Gestion de usuarios"),
                        new Tag().name("Administradores").description("Gestion de administradores"),
                        new Tag().name("Veterinarios").description("Gestion de veterinarios"),
                        new Tag().name("Recepcionistas").description("Gestion de recepcionistas"),
                        new Tag().name("Clientes").description("Gestion de clientes"),
                        new Tag().name("Servicios").description("Gestion de servicios"),
                        new Tag().name("Mascotas").description("Gestion de mascotas"),
                        new Tag().name("Citas").description("Gestion de citas"),
                        new Tag().name("Registros Medicos").description("Gestion de registros medicos"),
                        new Tag().name("Recetas").description("Gestion de recetas"),
                        new Tag().name("Items Receta").description("Gestion de items de receta")
                ))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
