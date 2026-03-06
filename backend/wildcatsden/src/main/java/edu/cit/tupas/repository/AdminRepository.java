package edu.cit.tupas.repository;

import edu.cit.tupas.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findByUserId(Long userId);
}
