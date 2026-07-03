package api_healthy_pet.Dtos.Response;

/**
 * Identidad del usuario autenticado con su rol para que el frontend
 * pueda enrutar y ocultar secciones:
 *  role ∈ { ADMIN, VETERINARIAN, RECEPTIONIST, CLIENT }
 */
public record MeResponse(String username, String role) {
}
