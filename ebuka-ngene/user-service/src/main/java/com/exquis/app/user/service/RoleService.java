package com.exquis.app.user.service;

import com.exquis.app.user.entity.Role;
import com.exquis.app.user.enums.RoleType;
import com.exquis.app.user.enums.StatusType;
import com.exquis.app.user.repository.RoleRepository;
import com.exquis.app.user.service.contract.RoleServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService implements RoleServiceContract {
    @Autowired RoleRepository roleRepository;
    @Autowired
    private RecordStatusService recordStatusService;

    /**@Override
    public Role findByRole(RoleType roleType) {
        return roleRepository.findByRole(roleType);
    }***/

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role findByRoleName(String role) {
        //return roleRepository.getOne(role);
        return roleRepository.findByRole(RoleType.valueOf(role));
    }

    @Override
    public Role addOrUpdate(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findByStatus(StatusType status) {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().filter(role -> role.getRecordStatus().getStatus().equals(status)).collect(Collectors.toList());
    }

    @Override
    public Boolean hasRole(Authentication auth, RoleType roleType) {
        if (auth.getAuthorities()
                .stream()
                .anyMatch(s -> s.getAuthority().equals(roleType.toString()))) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean hasRole(Set<Role> role, RoleType roleType) {
        if(role.stream().anyMatch(s->s.getId().equals(roleType)))
            return true;
        return false;
    }

    @Override
    public Boolean checkRoleStatus(Set<Role> roles, StatusType status) {
        if(roles.stream().anyMatch(s->s.getRecordStatus().getStatus().equals(status)))
            return true;
        return false;
    }
}
