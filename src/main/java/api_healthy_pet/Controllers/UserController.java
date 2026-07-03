package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.UserRequest;
import api_healthy_pet.Dtos.Response.UserResponse;
import api_healthy_pet.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(request));
    }
    @GetMapping("/{idUser}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long idUser){
        return ResponseEntity.ok().body(userService.findById(idUser));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PutMapping("/{idUser}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long idUser, @Valid @RequestBody UserRequest request){
        return ResponseEntity.ok().body(userService.updateUser(idUser, request));
    }

    @DeleteMapping("/{idUser}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long idUser){
        userService.deleteById(idUser);
        return ResponseEntity.noContent().build();
    }

}
