package api_healthy_pet.Dtos.Response;

public record ReceptionistResponse(Long idReceptionist, UserResponse user, String names, String lastNames, String email, String phoneNumber) {
}
