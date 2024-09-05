package org.security.kelurahanacademy.student.service;

import org.security.kelurahanacademy.student.model.request.ClassReq;
import org.security.kelurahanacademy.student.model.response.ClassRes;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    List<ClassRes> get();
    Optional<ClassRes> getById(String id);
    Optional<ClassRes> save(ClassReq request);
    Optional<ClassRes> update(ClassReq request, String id);
    Optional<ClassRes> delete(String id);
}
