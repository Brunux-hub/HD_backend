package api_healthy_pet.Controllers;

import api_healthy_pet.DTOs.request.MascotaRequest;
import api_healthy_pet.DTOs.response.MascotaResponse;
import api_healthy_pet.Services.MascotaService;
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
public class MascotaController {

    private final MascotaService mascotaService;

    @GetMapping("/mascotas")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA')")
    public ResponseEntity<List<MascotaResponse>> findAll() {
        return ResponseEntity.ok(mascotaService.findAll());
    }

    @GetMapping("/mascotas/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA','CLIENTE')")
    public ResponseEntity<MascotaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.findById(id));
    }

    @PostMapping("/mascotas")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA')")
    public ResponseEntity<MascotaResponse> create(@Valid @RequestBody MascotaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.create(request));
    }

    @PutMapping("/mascotas/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA')")
    public ResponseEntity<MascotaResponse> update(@PathVariable Long id, @Valid @RequestBody MascotaRequest request) {
        return ResponseEntity.ok(mascotaService.update(id, request));
    }

    @PatchMapping("/mascotas/{id}/activar")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA')")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        mascotaService.activate(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/mascotas/{id}/desactivar")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA')")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        mascotaService.deactivate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clientes/{idCliente}/mascotas")
    @PreAuthorize("hasAnyRole('ADMIN','RECEPCIONISTA','CLIENTE')")
    public ResponseEntity<List<MascotaResponse>> findByCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(mascotaService.findByCliente(idCliente));
    }

    @GetMapping("/mascotas/mis-mascotas")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<List<MascotaResponse>> findMine() {
        return ResponseEntity.ok(mascotaService.findMine());
    }
}
