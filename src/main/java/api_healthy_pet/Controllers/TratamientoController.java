package api_healthy_pet.Controllers;

import api_healthy_pet.DTOs.request.TratamientoRequest;
import api_healthy_pet.DTOs.response.TratamientoResponse;
import api_healthy_pet.Services.TratamientoService;
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
@Tag(name = "Tratamientos")
public class TratamientoController {

    private final TratamientoService tratamientoService;

    @GetMapping("/tratamientos")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO','CLIENTE')")
    public ResponseEntity<List<TratamientoResponse>> findAll() {
        return ResponseEntity.ok(tratamientoService.findAll());
    }

    @GetMapping("/tratamientos/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO','CLIENTE')")
    public ResponseEntity<TratamientoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tratamientoService.findById(id));
    }

    @PostMapping("/tratamientos")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO')")
    public ResponseEntity<TratamientoResponse> create(@Valid @RequestBody TratamientoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tratamientoService.create(request));
    }

    @PutMapping("/tratamientos/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO')")
    public ResponseEntity<TratamientoResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody TratamientoRequest request
    ) {
        return ResponseEntity.ok(tratamientoService.update(id, request));
    }

    @GetMapping("/registros-medicos/{idRegistro}/tratamientos")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO','CLIENTE')")
    public ResponseEntity<List<TratamientoResponse>> findByRegistro(@PathVariable Long idRegistro) {
        return ResponseEntity.ok(tratamientoService.findByRegistroMedico(idRegistro));
    }
}
