package com.exquis.app.user.repository;

import com.exquis.app.user.entity.User;
import com.exquis.app.user.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByGender(Gender gender);
}
