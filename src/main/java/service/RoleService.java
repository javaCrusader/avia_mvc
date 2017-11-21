package service;

import model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public boolean insert(Role role) {
        return roleRepository.save(role) != null;
    }

    public Role get(String name) {
        return roleRepository.findByName(name);
    }

    public Role get(Integer id) {
        return roleRepository.findOne(id);
    }

    public List<Role> getAll() {
        return roleRepository.findAllByOrderByIdAsc();
    }

    public boolean update(Role role) {
        Role old = roleRepository.findOne(role.getId());
        if (old == null)
            return false;
        old.setName(role.getName());
        old.setUsers(role.getUsers());
        return roleRepository.save(old) != null;
    }

}
