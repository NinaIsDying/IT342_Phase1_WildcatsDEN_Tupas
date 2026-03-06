package edu.cit.tupas.repository;

import edu.cit.tupas.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findByCourse(String course);
    List<StudentEntity> findByOrganization(String organization);
}