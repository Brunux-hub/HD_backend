package api_healthy_pet.Dtos.Request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
