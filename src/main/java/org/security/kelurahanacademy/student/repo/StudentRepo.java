package org.security.kelurahanacademy.student.repo;

import org.security.kelurahanacademy.student.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, String > {
}
