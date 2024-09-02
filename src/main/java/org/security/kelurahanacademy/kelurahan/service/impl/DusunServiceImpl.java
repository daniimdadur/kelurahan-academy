package org.security.kelurahanacademy.kelurahan.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.security.kelurahanacademy.kelurahan.model.request.DusunReq;
import org.security.kelurahanacademy.kelurahan.model.response.DusunRes;
import org.security.kelurahanacademy.kelurahan.repo.DusunRepo;
import org.security.kelurahanacademy.kelurahan.service.DusunService;
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
public class DusunServiceImpl implements DusunService {
    private final DusunRepo dusunRepo;

    @Override
    public List<DusunRes> get() {
        List<DusunEntity> result = this.dusunRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }
        return result.stream().map(DusunRes::new).collect(Collectors.toList());
    }

    @Override
    public Optional<DusunRes> getById(String id) {
        DusunEntity result = this.dusunRepo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }
        DusunRes res = new DusunRes(result);
        return Optional.of(res);
    }

    @Override
    public Optional<DusunRes> save(DusunReq request) {
        DusunEntity result = new DusunEntity();

        request.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(request, result);
        try {
            this.dusunRepo.save(result);
            log.info("save dusun to database success");
            return Optional.of(new DusunRes(result));
        } catch (Exception e) {
            log.error("save dusun to database failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<DusunRes> update(DusunReq request, String id) {
        DusunEntity result = this.dusunRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("dusun with id {} not found", id);
            return Optional.empty();
        }
        BeanUtils.copyProperties(request, result);
        try {
            this.dusunRepo.save(result);
            log.info("update dusun to database success");
            return Optional.of(new DusunRes(result));
        } catch (Exception e) {
            log.info("update dusun to database failed");
            return Optional.empty();
        }
    }

    @Override
    public Optional<DusunRes> delete(String id) {
        DusunEntity result = this.dusunRepo.findById(id).orElse(null);
        if (result == null) {
            log.warn("dusun with id {} not found", id);
            return Optional.empty();
        }

        try {
            this.dusunRepo.delete(result);
            log.info("delete dusun from database success");
            return Optional.of(new DusunRes(result));
        } catch (Exception e) {
            log.error("delete dusun from database failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
