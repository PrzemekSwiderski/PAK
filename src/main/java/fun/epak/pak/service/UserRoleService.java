package fun.epak.pak.service;

import fun.epak.pak.model.user.UserRole;
import fun.epak.pak.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public void saveRole(UserRole userRole){
        userRoleRepository.save(userRole);
    }
}
