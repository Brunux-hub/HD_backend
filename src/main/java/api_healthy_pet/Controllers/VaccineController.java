package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.VaccineRequest;
import api_healthy_pet.Dtos.Response.VaccineResponse;
import api_healthy_pet.Services.VaccineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccine")
@RequiredArgsConstructor
public class VaccineController {

    private final VaccineService vaccineService;

    @PostMapping
    public ResponseEntity<VaccineResponse> createVaccine(@Valid @RequestBody VaccineRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vaccineService.create(request));
    }
    @GetMapping("/{idVaccine}")
    public ResponseEntity<VaccineResponse> getVaccineByID(@PathVariable Long idVaccine){
        return ResponseEntity.ok().body(vaccineService.findById(idVaccine));
    }

    @GetMapping
    public ResponseEntity<List<VaccineResponse>> getAllVaccines(){
        return ResponseEntity.ok().body(vaccineService.findAll());
    }

    @PutMapping("/{idVaccine}")
    public ResponseEntity<VaccineResponse> updateVaccineById(@PathVariable Long idVaccine, @Valid @RequestBody VaccineRequest request){
        return ResponseEntity.ok().body(vaccineService.updateById(idVaccine, request));
    }

    @DeleteMapping("/{idVaccine}")
    public ResponseEntity<Void> deleteVaccineById(@PathVariable Long idVaccine){
        vaccineService.deleteById(idVaccine);
        return ResponseEntity.noContent().build();
    }

}
