package com.exquis.app.user.repository;

import com.exquis.app.user.entity.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordStatusRepository extends JpaRepository<RecordStatus, Long> {
}
