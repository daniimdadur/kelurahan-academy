package org.security.kelurahanacademy.student.repo;

import org.security.kelurahanacademy.student.model.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepo extends JpaRepository<ClassEntity, String > {
}
