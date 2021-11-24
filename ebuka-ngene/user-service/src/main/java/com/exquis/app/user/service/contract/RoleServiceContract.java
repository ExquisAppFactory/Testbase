package com.exquis.app.user.service.contract;

import com.exquis.app.user.entity.Role;
import com.exquis.app.user.enums.RoleType;
import com.exquis.app.user.enums.StatusType;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;

public interface RoleServiceContract {
    //Role findByRole(RoleType roleType);
    Role findById(Long id);

    Role findByRoleName(String role);

    Role addOrUpdate(Role role);

    List<Role> findAll();
    // fill all roles by status
    List<Role> findByStatus(StatusType status);
    /** Check if a user has a certain role* */
    Boolean hasRole(Authentication auth, RoleType roleType);

    Boolean hasRole(Set<Role> role, RoleType roleType);

    Boolean checkRoleStatus(Set<Role> roles, StatusType status);
}
