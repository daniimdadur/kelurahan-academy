package org.security.kelurahanacademy.kelurahan.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.kelurahanacademy.kelurahan.model.entity.RTEntity;
import org.security.kelurahanacademy.kelurahan.model.entity.RWEntity;
import org.security.kelurahanacademy.kelurahan.model.request.RTReq;
import org.security.kelurahanacademy.kelurahan.model.response.RTRes;
import org.security.kelurahanacademy.kelurahan.repo.RTRepo;
import org.security.kelurahanacademy.kelurahan.repo.RWRepo;
import org.security.kelurahanacademy.kelurahan.service.RTService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RTServiceImpl implements RTService {
    private final RTRepo rtRepo;
    private final RWRepo rwRepo;

    @Override
    public List<RTRes> get() {
        List<RTEntity> result = this.rtRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }
        return result.stream().map(RTRes::new).collect(Collectors.toList());
    }

    @Override
    public Optional<RTRes> getById(String id) {
        RTEntity result = this.rtRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("RT with id {} not found", id);
            return Optional.empty();
        }
        RTRes res = new RTRes(result);
        return Optional.of(res);
    }

    @Override
    public Optional<RTRes> save(RTReq request) {
        RWEntity rwEntity = this.rwRepo.findById(request.getRwId()).orElse(null);
        if (rwEntity == null) {
            return Optional.empty();
        }

        RTEntity result = new RTEntity();

        request.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(request, result);
        result.setRw(rwEntity);
        try {
            this.rtRepo.save(result);
            log.info("save rt success");
            return Optional.of(new RTRes(result));
        } catch (Exception e) {
            log.error("save rt failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<RTRes> update(RTReq request, String id) {
        RTEntity result = this.rtRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("Rt with id {} not found", id);
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, result);
        try {
            this.rtRepo.save(result);
            log.info("update rt success");
            return Optional.of(new RTRes(result));
        } catch (Exception e) {
            log.error("update rt failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<RTRes> delete(String id) {
        RTEntity result = this.rtRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("rt with id {} not found", id);
            return Optional.empty();
        }

        try {
            this.rtRepo.delete(result);
            log.info("delete rt success");
            return Optional.of(new RTRes(result));
        } catch (Exception e) {
            log.error("delete rt failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
