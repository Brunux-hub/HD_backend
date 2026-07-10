package api_healthy_pet.Controllers;

import api_healthy_pet.DTOs.request.AdministradorRequest;
import api_healthy_pet.DTOs.response.AdministradorResponse;
import api_healthy_pet.Services.AdministradorService;
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
@RequestMapping("/api/v1/administradores")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdministradorController {

    private final AdministradorService administradorService;

    @GetMapping
    public ResponseEntity<List<AdministradorResponse>> findAll() {
        return ResponseEntity.ok(administradorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(administradorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AdministradorResponse> create(@Valid @RequestBody AdministradorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(administradorService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministradorResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody AdministradorRequest request
    ) {
        return ResponseEntity.ok(administradorService.update(id, request));
    }
}
