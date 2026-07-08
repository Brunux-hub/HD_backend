package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.ClienteRequest;
import api_healthy_pet.Dtos.Response.ClienteResponse;
import api_healthy_pet.Entities.Cliente;
import api_healthy_pet.Exceptions.ClienteException;
import api_healthy_pet.Mappers.ClienteMapper;
import api_healthy_pet.Repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;

    public ClienteResponse create (ClienteRequest request){
        return clienteMapper.toResponse(clienteRepository.save(clienteMapper.toEntity(request)));
    }

    public ClienteResponse findById (Long userId){
        return clienteRepository.findById(userId)
                .map(clienteMapper::toResponse)
                .orElseThrow(() -> new ClienteException("Cliente no encontrado"));
    }

    public List<ClienteResponse> findAll(){
        return clienteRepository.findAll()
                .stream().map(clienteMapper::toResponse).toList();
    }

    public Optional<ClienteResponse> findByEmail(String email){
        return clienteRepository.findByUser_Email(email).map(clienteMapper::toResponse);
    }

    public ClienteResponse updateById (Long userId, ClienteRequest request){
        Cliente cliente = clienteRepository.findById(userId)
                .orElseThrow(() -> new ClienteException("Cliente no encontrado"));

        clienteMapper.updateEntityFromRequest(request, cliente);

        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    public void deleteById (Long userId) {
        if (!clienteRepository.existsById(userId)){
            throw new ClienteException("Cliente no encontrado");
        }
        clienteRepository.deleteById(userId);
    }

}
