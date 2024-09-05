package org.security.kelurahanacademy.student.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.kelurahanacademy.student.model.entity.StudentEntity;
import org.security.kelurahanacademy.student.model.request.StudentReq;
import org.security.kelurahanacademy.student.model.response.StudentRes;
import org.security.kelurahanacademy.student.repo.StudentRepo;
import org.security.kelurahanacademy.student.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;

    @Override
    public List<StudentRes> get() {
        List<StudentEntity> result = studentRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        return result.stream().map(studentEntity -> new StudentRes(studentEntity)).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentRes> getById(String id) {
        StudentEntity result = this.studentRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("student with id {} not found", id);
            return Optional.empty();
        }

        StudentRes response = new StudentRes(result);
        return Optional.of(response);
    }

    @Override
    public Optional<StudentRes> save(StudentReq studentReq) {
        StudentEntity result = new StudentEntity();

        studentReq.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(studentReq, result);
        try {
            this.studentRepo.save(result);
            return Optional.of(new StudentRes(result));
        } catch (Exception e) {
            log.error("save student failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<StudentRes> update(StudentReq studentReq, String id) {
        StudentEntity result = this.studentRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("student with id {} not foudn", id);
            return Optional.empty();
        }

        BeanUtils.copyProperties(studentReq, result);
        try {
            this.studentRepo.save(result);
            return Optional.of(new StudentRes(result));
        } catch (Exception e) {
            log.error("update student failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<StudentRes> delete(String id) {
        StudentEntity result = this.studentRepo.findById(id).orElse(null);
        if (result == null) {
            log.warn("student with id {} not found", id);
            return Optional.empty();
        }

        try {
            this.studentRepo.delete(result);
            return Optional.of(new StudentRes(result));
        } catch (Exception e) {
            log.error("delete student failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
