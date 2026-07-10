package api_healthy_pet.Controllers;

import api_healthy_pet.DTOs.request.CitaEstadoRequest;
import api_healthy_pet.DTOs.request.CitaRequest;
import api_healthy_pet.DTOs.response.CitaResponse;
import api_healthy_pet.Services.CitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
public class CitaController {

    private final CitaService citaService;

    @GetMapping("/citas")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA')")
    public ResponseEntity<List<CitaResponse>> findAll() {
        return ResponseEntity.ok(citaService.findAll());
    }

    @GetMapping("/citas/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA','VETERINARIO','CLIENTE')")
    public ResponseEntity<CitaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.findById(id));
    }

    @PostMapping("/citas")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA')")
    public ResponseEntity<CitaResponse> create(@Valid @RequestBody CitaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(citaService.create(request));
    }

    @PutMapping("/citas/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA')")
    public ResponseEntity<CitaResponse> update(@PathVariable Long id, @Valid @RequestBody CitaRequest request) {
        return ResponseEntity.ok(citaService.update(id, request));
    }

    @PatchMapping("/citas/{id}/estado")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA')")
    public ResponseEntity<CitaResponse> changeEstado(
            @PathVariable Long id,
            @Valid @RequestBody CitaEstadoRequest request
    ) {
        return ResponseEntity.ok(citaService.changeEstado(id, request));
    }

    @GetMapping("/citas/mis-citas")
    @PreAuthorize("hasAnyRole('CLIENTE','VETERINARIO')")
    public ResponseEntity<List<CitaResponse>> findMine() {
        return ResponseEntity.ok(citaService.findMine());
    }

    @GetMapping("/veterinarios/{idVeterinario}/citas")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA','VETERINARIO')")
    public ResponseEntity<List<CitaResponse>> findByVeterinario(@PathVariable Long idVeterinario) {
        return ResponseEntity.ok(citaService.findByVeterinario(idVeterinario));
    }

    @GetMapping("/clientes/{idCliente}/citas")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA','CLIENTE')")
    public ResponseEntity<List<CitaResponse>> findByCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(citaService.findByCliente(idCliente));
    }
}
