package org.security.kelurahanacademy.kelurahan.service;

import org.security.kelurahanacademy.kelurahan.model.request.RTReq;
import org.security.kelurahanacademy.kelurahan.model.response.RTRes;

import java.util.List;
import java.util.Optional;

public interface RTService {
    List<RTRes> get();
    Optional<RTRes> get(String id);
    Optional<RTRes> save(RTReq request);
    Optional<RTRes> update(RTReq request, String id);
    Optional<RTRes> delete(String id);
}
