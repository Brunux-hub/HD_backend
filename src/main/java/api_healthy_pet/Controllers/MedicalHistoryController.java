package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.MedicalHistoryRequest;
import api_healthy_pet.Dtos.Response.MedicalHistoryResponse;
import api_healthy_pet.Services.MedicalHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical_history")
@RequiredArgsConstructor
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    @PostMapping
    public ResponseEntity<MedicalHistoryResponse> createMedicalHistory(@Valid @RequestBody MedicalHistoryRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicalHistoryService.create(request));
    }
    @GetMapping("/{idMedicalHistory}")
    public ResponseEntity<MedicalHistoryResponse> getMedicalHistoryByID(@PathVariable Long idMedicalHistory){
        return ResponseEntity.ok().body(medicalHistoryService.findById(idMedicalHistory));
    }

    @GetMapping
    public ResponseEntity<List<MedicalHistoryResponse>> getAllMedicalHistory(){
        return ResponseEntity.ok().body(medicalHistoryService.findAll());
    }

    @PutMapping("/{idMedicalHistory}")
    public ResponseEntity<MedicalHistoryResponse> updateMedicalHistoryById(@PathVariable Long idMedicalHistory, @Valid @RequestBody MedicalHistoryRequest request){
        return ResponseEntity.ok().body(medicalHistoryService.updateById(idMedicalHistory, request));
    }

    @DeleteMapping("/{idMedicalHistory}")
    public ResponseEntity<Void> deleteMedicalHistoryById(@PathVariable Long idMedicalHistory){
        medicalHistoryService.deleteById(idMedicalHistory);
        return ResponseEntity.noContent().build();
    }

}
