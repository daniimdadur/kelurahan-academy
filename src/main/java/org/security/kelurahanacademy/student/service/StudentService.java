package org.security.kelurahanacademy.student.service;

import org.security.kelurahanacademy.student.model.request.StudentReq;
import org.security.kelurahanacademy.student.model.response.StudentRes;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentRes> get();
    Optional<StudentRes> getById(String id);
    Optional<StudentRes> save(StudentReq studentReq);
    Optional<StudentRes> update(StudentReq studentReq, String id);
    Optional<StudentRes> delete(String id);
}
