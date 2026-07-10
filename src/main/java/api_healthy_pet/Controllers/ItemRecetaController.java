package api_healthy_pet.Controllers;

import api_healthy_pet.DTOs.request.ItemRecetaRequest;
import api_healthy_pet.DTOs.response.ItemRecetaResponse;
import api_healthy_pet.Services.ItemRecetaService;
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
public class ItemRecetaController {

    private final ItemRecetaService itemRecetaService;

    @GetMapping("/items-receta")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO','CLIENTE')")
    public ResponseEntity<List<ItemRecetaResponse>> findAll() {
        return ResponseEntity.ok(itemRecetaService.findAll());
    }

    @GetMapping("/items-receta/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO','CLIENTE')")
    public ResponseEntity<ItemRecetaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemRecetaService.findById(id));
    }

    @PostMapping("/items-receta")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO')")
    public ResponseEntity<ItemRecetaResponse> create(@Valid @RequestBody ItemRecetaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemRecetaService.create(request));
    }

    @PutMapping("/items-receta/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO')")
    public ResponseEntity<ItemRecetaResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ItemRecetaRequest request
    ) {
        return ResponseEntity.ok(itemRecetaService.update(id, request));
    }

    @GetMapping("/recetas/{idReceta}/items")
    @PreAuthorize("hasAnyRole('ADMIN','VETERINARIO','CLIENTE')")
    public ResponseEntity<List<ItemRecetaResponse>> findByReceta(@PathVariable Long idReceta) {
        return ResponseEntity.ok(itemRecetaService.findByReceta(idReceta));
    }
}
