package root.timemanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.timemanagementapp.database.model.Role;
import root.timemanagementapp.database.repo.RoleRepository;
import root.timemanagementapp.dto.RoleNames;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) throws Exception {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()) {
            return role.get();
        } else {
            throw new Exception("Not found role by name=" + name);
        }
    }

    public void createRolesIfNotExists() {
        if (roleRepository.findByName(RoleNames.DEV_ROLE).isEmpty()) {
            roleRepository.save(new Role(1L, RoleNames.DEV_ROLE));
        }
        if (roleRepository.findByName(RoleNames.MASTER_ROLE).isEmpty()) {
            roleRepository.save(new Role(2L, RoleNames.MASTER_ROLE));
        }
    }

}
