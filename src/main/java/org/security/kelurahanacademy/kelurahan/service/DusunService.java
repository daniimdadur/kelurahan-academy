package org.security.kelurahanacademy.kelurahan.service;

import org.security.kelurahanacademy.kelurahan.model.request.DusunReq;
import org.security.kelurahanacademy.kelurahan.model.response.DusunRes;

import java.util.List;
import java.util.Optional;

public interface DusunService {
    List<DusunRes> get();
    Optional<DusunRes> getById(String id);
    Optional<DusunRes> save(DusunReq request);
    Optional<DusunRes> update(DusunReq request, String id);
    Optional<DusunRes> delete(String id);
}
