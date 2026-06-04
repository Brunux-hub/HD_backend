package api_healthy_pet.Dtos.Request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String role;
}