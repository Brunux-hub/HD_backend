package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.AppointmentRequest;
import api_healthy_pet.Dtos.Response.AppointmentResponse;
import api_healthy_pet.Services.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('ADMIN')")
    public ResponseEntity<AppointmentResponse> createOwner(@Valid @RequestBody AppointmentRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(appointmentService.create(request));
    }
    @GetMapping("/{idAppointment}")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('ADMIN')")
    public ResponseEntity<AppointmentResponse> getOwnerByID(@PathVariable Long idAppointment){
        return ResponseEntity.ok().body(appointmentService.findById(idAppointment));
    }

    @GetMapping
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('ADMIN')")
    public ResponseEntity<List<AppointmentResponse>> getAllOwners(){
        return ResponseEntity.ok().body(appointmentService.findAll());
    }

    @PutMapping("/{idAppointment}")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('ADMIN')")
    public ResponseEntity<AppointmentResponse> updateOwnerById(@PathVariable Long idAppointment, @Valid @RequestBody AppointmentRequest request){
        return ResponseEntity.ok().body(appointmentService.updateById(idAppointment, request));
    }

    @DeleteMapping("/{idAppointment}")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteOwnerById(@PathVariable Long idAppointment){
        appointmentService.deleteById(idAppointment);
        return ResponseEntity.noContent().build();
    }

}
