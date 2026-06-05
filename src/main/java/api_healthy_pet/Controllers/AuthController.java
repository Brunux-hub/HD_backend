package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.LoginRequest;
import api_healthy_pet.Dtos.Request.UserRequest;
import api_healthy_pet.Dtos.Response.UserResponse;
import api_healthy_pet.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest request){
        UserResponse created = userService.createUser(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid LoginRequest request){
        UserResponse user = userService.authenticate(request.username(), request.password());
        return ResponseEntity.ok(user);
    }

}
