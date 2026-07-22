package api_healthy_pet.Controllers;

import api_healthy_pet.DTOs.request.RegistroMedicoRequest;
import api_healthy_pet.DTOs.response.RegistroMedicoResponse;
import api_healthy_pet.Services.RegistroMedicoService;
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
@Tag(name = "Registros Medicos")
public class RegistroMedicoController {

    private final RegistroMedicoService registroMedicoService;

    @GetMapping("/registros-medicos")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO','CLIENTE')")
    public ResponseEntity<List<RegistroMedicoResponse>> findAll() {
        return ResponseEntity.ok(registroMedicoService.findAll());
    }

    @GetMapping("/registros-medicos/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO','CLIENTE')")
    public ResponseEntity<RegistroMedicoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(registroMedicoService.findById(id));
    }

    @PostMapping("/registros-medicos")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO')")
    public ResponseEntity<RegistroMedicoResponse> create(@Valid @RequestBody RegistroMedicoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registroMedicoService.create(request));
    }

    @PutMapping("/registros-medicos/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO')")
    public ResponseEntity<RegistroMedicoResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody RegistroMedicoRequest request
    ) {
        return ResponseEntity.ok(registroMedicoService.update(id, request));
    }

    @GetMapping("/citas/{idCita}/registro-medico")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO','CLIENTE')")
    public ResponseEntity<RegistroMedicoResponse> findByCita(@PathVariable Long idCita) {
        return ResponseEntity.ok(registroMedicoService.findByCita(idCita));
    }
}
