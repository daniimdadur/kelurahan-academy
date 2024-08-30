package org.security.kelurahanacademy.kelurahan.repo;

import org.security.kelurahanacademy.kelurahan.model.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepo extends JpaRepository<PeopleEntity, String> {
}
