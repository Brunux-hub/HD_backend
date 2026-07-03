package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.ClientRegisterRequest;
import api_healthy_pet.Dtos.Request.LoginRequest;
import api_healthy_pet.Dtos.Response.AuthResponse;
import api_healthy_pet.Dtos.Response.MeResponse;
import api_healthy_pet.Dtos.Response.OwnerResponse;
import api_healthy_pet.Services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // Registro público de clientes: crea User (login) + Owner (ficha).
    @PostMapping("/register")
    public ResponseEntity<OwnerResponse> register(@Valid @RequestBody ClientRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerClient(request));
    }

    // Rol fino del usuario autenticado (para enrutar y ocultar secciones en el front).
    @GetMapping("/me")
    public ResponseEntity<MeResponse> me(Principal principal) {
        return ResponseEntity.ok(authService.me(principal.getName()));
    }
}
