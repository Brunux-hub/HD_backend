package api_healthy_pet.Controllers;

import api_healthy_pet.DTOs.request.RecetaRequest;
import api_healthy_pet.DTOs.response.RecetaResponse;
import api_healthy_pet.Services.RecetaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Recetas")
public class RecetaController {

    private final RecetaService recetaService;

    @GetMapping("/recetas")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO','CLIENTE')")
    public ResponseEntity<List<RecetaResponse>> findAll() {
        return ResponseEntity.ok(recetaService.findAll());
    }

    @GetMapping("/recetas/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO','CLIENTE')")
    public ResponseEntity<RecetaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(recetaService.findById(id));
    }

    @PostMapping("/recetas")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO')")
    public ResponseEntity<RecetaResponse> create(@Valid @RequestBody RecetaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recetaService.create(request));
    }

    @PutMapping("/recetas/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO')")
    public ResponseEntity<RecetaResponse> update(@PathVariable Long id, @Valid @RequestBody RecetaRequest request) {
        return ResponseEntity.ok(recetaService.update(id, request));
    }

    @GetMapping("/registros-medicos/{idRegistro}/recetas")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO','CLIENTE')")
    public ResponseEntity<List<RecetaResponse>> findByRegistro(@PathVariable Long idRegistro) {
        return ResponseEntity.ok(recetaService.findByRegistroMedico(idRegistro));
    }
}
