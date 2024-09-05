package org.security.kelurahanacademy.student.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.kelurahanacademy.student.model.entity.ClassEntity;
import org.security.kelurahanacademy.student.model.request.ClassReq;
import org.security.kelurahanacademy.student.model.response.ClassRes;
import org.security.kelurahanacademy.student.repo.ClassRepo;
import org.security.kelurahanacademy.student.service.ClassService;
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
public class ClassServiceImpl implements ClassService {
    private final ClassRepo classRepo;

    @Override
    public List<ClassRes> get() {
        List<ClassEntity> result = this.classRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        return result.stream().map(schoolClassEntity -> new ClassRes(schoolClassEntity)).collect(Collectors.toList());
    }

    @Override
    public Optional<ClassRes> getById(String id) {
        ClassEntity result = this.classRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("class with id {} not found", id);
            return Optional.empty();
        }

        return Optional.of(new ClassRes(result));
    }

    @Override
    public Optional<ClassRes> save(ClassReq request) {
        ClassEntity result = new ClassEntity();

        request.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(request, result);
        try {
            this.classRepo.save(result);
            return Optional.of(new ClassRes(result));
        } catch (Exception e) {
            log.error("save class failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ClassRes> update(ClassReq request, String id) {
        ClassEntity result = this.classRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("class with id {} not found", id);
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, result);
        try {
            this.classRepo.save(result);
            return Optional.of(new ClassRes(result));
        } catch (Exception e) {
            log.error("update class failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ClassRes> delete(String id) {
        ClassEntity result = this.classRepo.findById(id).orElse(null);
        if (result == null) {
            log.warn("class with id {} not found", id);
            return Optional.empty();
        }

        try {
            this.classRepo.delete(result);
            return Optional.of(new ClassRes(result));
        } catch (Exception e) {
            log.error("delete class failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
