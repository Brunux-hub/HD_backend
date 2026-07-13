package api_healthy_pet.Controllers;

import api_healthy_pet.DTOs.request.RecepcionistaRequest;
import api_healthy_pet.DTOs.response.RecepcionistaResponse;
import api_healthy_pet.Services.RecepcionistaService;
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
@RequestMapping("/api/v1/recepcionistas")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Recepcionistas")
public class RecepcionistaController {

    private final RecepcionistaService recepcionistaService;

    @GetMapping
    public ResponseEntity<List<RecepcionistaResponse>> findAll() {
        return ResponseEntity.ok(recepcionistaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecepcionistaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(recepcionistaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RecepcionistaResponse> create(@Valid @RequestBody RecepcionistaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recepcionistaService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecepcionistaResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody RecepcionistaRequest request
    ) {
        return ResponseEntity.ok(recepcionistaService.update(id, request));
    }
}
