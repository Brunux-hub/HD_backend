package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Request.ClienteRequest;
import api_healthy_pet.Dtos.Response.ClienteResponse;
import api_healthy_pet.Services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> createCliente(@Valid @RequestBody ClienteRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteService.create(request));
    }

    @GetMapping("/me")
    public ResponseEntity<ClienteResponse> getMyCliente(Principal principal){
        return clienteService.findByEmail(principal.getName())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ClienteResponse> getClienteById(@PathVariable Long userId){
        return ResponseEntity.ok().body(clienteService.findById(userId));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> getAllClientes(){
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ClienteResponse> updateClienteById(@PathVariable Long userId, @Valid @RequestBody ClienteRequest request){
        return ResponseEntity.ok().body(clienteService.updateById(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long userId){
        clienteService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

}
