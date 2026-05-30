package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.VaccinationRequest;
import api_healthy_pet.Dtos.Response.VaccinationResponse;
import api_healthy_pet.Services.VaccinationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccination")
@RequiredArgsConstructor
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @PostMapping
    public ResponseEntity<VaccinationResponse> createVaccination(@Valid @RequestBody VaccinationRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vaccinationService.create(request));
    }
    @GetMapping("/{idVaccination}")
    public ResponseEntity<VaccinationResponse> getVaccinationByID(@PathVariable Long idVaccination){
        return ResponseEntity.ok().body(vaccinationService.findById(idVaccination));
    }

    @GetMapping
    public ResponseEntity<List<VaccinationResponse>> getAllVaccinations(){
        return ResponseEntity.ok().body(vaccinationService.findAll());
    }

    @PutMapping("/{idVaccination}")
    public ResponseEntity<VaccinationResponse> updateVaccinationById(@PathVariable Long idVaccination, @Valid @RequestBody VaccinationRequest request){
        return ResponseEntity.ok().body(vaccinationService.updateById(idVaccination, request));
    }

    @DeleteMapping("/{idVaccination}")
    public ResponseEntity<Void> deleteVaccinationById(@PathVariable Long idVaccination){
        vaccinationService.deleteById(idVaccination);
        return ResponseEntity.noContent().build();
    }


}
