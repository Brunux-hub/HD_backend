package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Response.AdminResponse;
import api_healthy_pet.Services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/{userId}")
    public ResponseEntity<AdminResponse> getAdminById(@PathVariable Long userId){
        return ResponseEntity.ok().body(adminService.findById(userId));
    }

    @GetMapping
    public ResponseEntity<List<AdminResponse>> getAllAdmins(){
        return ResponseEntity.ok().body(adminService.findAll());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable Long userId){
        adminService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

}
