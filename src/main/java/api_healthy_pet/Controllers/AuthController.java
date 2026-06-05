package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.LoginRequest;
import api_healthy_pet.Dtos.Response.LoginResponse;
import api_healthy_pet.Services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Tag(name = "Autenticacion", description = "Endpoints de login")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Iniciar sesion", description = "Recibe email y contrasena, retorna un token JWT")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(summary = "Obtener usuario actual", description = "Retorna el email del usuario autenticado")
    @GetMapping("/me")
    public ResponseEntity<String> me(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }
}
