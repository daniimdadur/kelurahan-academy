package org.security.kelurahanacademy.kelurahan.service;

import org.security.kelurahanacademy.kelurahan.model.request.PeopleReq;
import org.security.kelurahanacademy.kelurahan.model.response.PeopleRes;

import java.util.List;
import java.util.Optional;

public interface PeopleService {
    List<PeopleRes> get();
    Optional<PeopleRes> getById(String  id);
    Optional<PeopleRes> save(PeopleReq request);
    Optional<PeopleRes> update(PeopleReq request, String id);
    Optional<PeopleRes> delete(String id);
}
