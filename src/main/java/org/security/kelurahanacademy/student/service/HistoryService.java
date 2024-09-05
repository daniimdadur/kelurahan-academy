package org.security.kelurahanacademy.student.service;

import org.security.kelurahanacademy.student.model.request.HistoryReq;
import org.security.kelurahanacademy.student.model.response.HistoryRes;

import java.util.List;
import java.util.Optional;

public interface HistoryService {
    List<HistoryRes> get();
    Optional<HistoryRes> getById(String id);
    Optional<HistoryRes> save(HistoryReq request);
    Optional<HistoryRes> update(HistoryReq request, String id);
    Optional<HistoryRes> delete(String id);
}
