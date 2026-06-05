package api_healthy_pet.Dtos.Response;

public record VeterinarianResponse(Long idVeterinarian, UserResponse userResponse, String names, String lastNames, String numberLicense, String specialty, String email, String phoneNumber) {
}
