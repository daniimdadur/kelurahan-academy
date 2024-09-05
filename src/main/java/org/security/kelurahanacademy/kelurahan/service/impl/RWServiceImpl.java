package org.security.kelurahanacademy.kelurahan.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.kelurahanacademy.kelurahan.model.entity.RWEntity;
import org.security.kelurahanacademy.kelurahan.model.request.RWReq;
import org.security.kelurahanacademy.kelurahan.model.response.RWRes;
import org.security.kelurahanacademy.kelurahan.repo.RWRepo;
import org.security.kelurahanacademy.kelurahan.service.RWService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RWServiceImpl implements RWService {
    private final RWRepo rwRepo;

    @Override
    public List<RWRes> get() {
        List<RWEntity> result = this.rwRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }
        return result.stream().map(RWRes::new).collect(Collectors.toList());
    }

    @Override
    public Optional<RWRes> getById(String id) {
        RWEntity result = this.rwRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("rw with id {} Not found", id);
            return Optional.empty();
        }
        RWRes res = new RWRes(result);
        return Optional.of(res);
    }

    @Override
    public Optional<RWRes> save(RWReq request) {
        RWEntity result = new RWEntity();

        request.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(request, result);
        try {
            this.rwRepo.save(result);
            log.info("save rw to database success");
            return Optional.of(new RWRes(result));
        } catch (Exception e) {
            log.error("save rw to database failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<RWRes> update(RWReq request, String id) {
        RWEntity result = this.rwRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("rw with id {} not found", id);
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, result);
        try {
            this.rwRepo.save(result);
            log.info("update rw to database success");
            return Optional.of(new RWRes(result));
        } catch (Exception e) {
            log.error("update rw to database failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<RWRes> delete(String id) {
        RWEntity result = this.rwRepo.findById(id).orElse(null);
        if (result == null) {
            log.warn("rw with id {} not found", id);
            return Optional.empty();
        }

        try {
            this.rwRepo.delete(result);
            log.info("delete rw from database success");
            return Optional.of(new RWRes(result));
        } catch (Exception e) {
            log.error("delete rw from database failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
