package org.security.kelurahanacademy.student.repo;

import org.security.kelurahanacademy.student.model.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepo extends JpaRepository<HistoryEntity, String > {
}
