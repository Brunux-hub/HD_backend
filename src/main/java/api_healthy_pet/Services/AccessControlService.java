package api_healthy_pet.Services;

import api_healthy_pet.Repositories.ReceptionistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessControlService {

    private final ReceptionistRepository receptionistRepository;

    /** Exige que el usuario logueado tenga ficha de recepcionista (si no, 403). */
    public void requireReceptionist() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (auth != null) ? auth.getName() : null;

        if (username == null || !receptionistRepository.existsByUser_Username(username)) {
            throw new AccessDeniedException("Solo un recepcionista puede realizar esta acción.");
        }
    }
}
