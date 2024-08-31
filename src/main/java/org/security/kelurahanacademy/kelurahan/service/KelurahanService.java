package org.security.kelurahanacademy.kelurahan.service;

import org.security.kelurahanacademy.kelurahan.model.request.KelurahanReq;
import org.security.kelurahanacademy.kelurahan.model.response.KelurahanRes;

import java.util.List;
import java.util.Optional;

public interface KelurahanService {
    List<KelurahanRes> get();
    Optional<KelurahanRes> getById(String id);
    Optional<KelurahanRes> save(KelurahanReq request);
    Optional<KelurahanRes> update(KelurahanReq request, String id);
    Optional<KelurahanRes> delete(String id);
}
