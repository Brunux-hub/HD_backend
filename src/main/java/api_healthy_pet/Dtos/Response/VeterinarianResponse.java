package api_healthy_pet.Dtos.Response;

public record VeterinarianResponse(Long idVeterinarian, UserResponse user, String names, String lastNames, String numberLicense, String specialty, String email, String phoneNumber) {
}
