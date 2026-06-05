package api_healthy_pet.Services;

import api_healthy_pet.Dtos.Request.UserRequest;
import api_healthy_pet.Dtos.Response.UserResponse;
import api_healthy_pet.Entities.User;
import api_healthy_pet.Exceptions.UserException;
import api_healthy_pet.Mappers.UserMapper;
import api_healthy_pet.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserResponse createUser (UserRequest request){
        return userMapper.toResponse(userRepository.save(userMapper.toEntity(request)));
    }

    public UserResponse findById (Long idUser){
        return userRepository.findById(idUser)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new UserException("Usuario no encontrado"));
    }

    public List<UserResponse> findAll(){
        return userRepository.findAll()
                .stream().map(userMapper::toResponse).toList();
    }

    public UserResponse updateUser (Long idUser, UserRequest request){
        User user = userRepository.findById(idUser)
                        .orElseThrow(() -> new UserException("Usuario no encontrado"));

        userMapper.updateEntityFromRequest(request, user);

        return userMapper.toResponse(userRepository.save(user));
    }

    public void deleteById (Long idUser) {
        if (!userRepository.existsById(idUser)){
            throw new UserException("Usuario no encontrado");
        }
        userRepository.deleteById(idUser);
    }
}
