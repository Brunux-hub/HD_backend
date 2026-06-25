package api_healthy_pet.Dtos.Response;

public record VaccineResponse(Long idVaccine, String name, String description, String manufacturer, Integer requiredDose) {
}
