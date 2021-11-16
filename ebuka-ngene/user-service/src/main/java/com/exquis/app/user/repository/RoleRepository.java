package com.exquis.app.user.repository;

import com.exquis.app.user.entity.Role;
import com.exquis.app.user.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(RoleType roleType);
}
