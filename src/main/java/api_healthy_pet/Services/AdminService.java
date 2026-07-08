package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.AdminRequest;
import api_healthy_pet.Dtos.Response.AdminResponse;
import api_healthy_pet.Entities.Admin;
import api_healthy_pet.Exceptions.AdminException;
import api_healthy_pet.Mappers.AdminMapper;
import api_healthy_pet.Repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;
    private final AdminRepository adminRepository;

    public AdminResponse findById (Long userId){
        return adminRepository.findById(userId)
                .map(adminMapper::toResponse)
                .orElseThrow(() -> new AdminException("Admin no encontrado"));
    }

    public List<AdminResponse> findAll(){
        return adminRepository.findAll()
                .stream().map(adminMapper::toResponse).toList();
    }

    public void deleteById (Long userId) {
        if (!adminRepository.existsById(userId)){
            throw new AdminException("Admin no encontrado");
        }
        adminRepository.deleteById(userId);
    }

}
