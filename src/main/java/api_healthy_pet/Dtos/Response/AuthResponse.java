package api_healthy_pet.Dtos.Response;

// token + rol + username para que el frontend enrute inmediatamente tras login.
public record AuthResponse(String token, String role, String username) {
}
