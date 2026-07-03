package api_healthy_pet.Dtos.Response;

/**
 * Identidad del usuario autenticado con su rol "fino" para que el frontend
 * pueda enrutar y ocultar secciones:
 *  role ∈ { ADMIN, VETERINARIAN, RECEPTIONIST, CLIENT, WORKER }
 */
public record MeResponse(String username, String role) {
}
