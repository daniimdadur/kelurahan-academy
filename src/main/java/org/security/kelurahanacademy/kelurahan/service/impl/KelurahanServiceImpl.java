package org.security.kelurahanacademy.kelurahan.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.kelurahanacademy.kelurahan.model.entity.KelurahanEntity;
import org.security.kelurahanacademy.kelurahan.model.request.KelurahanReq;
import org.security.kelurahanacademy.kelurahan.model.response.KelurahanRes;
import org.security.kelurahanacademy.kelurahan.repo.KelurahanRepo;
import org.security.kelurahanacademy.kelurahan.service.KelurahanService;
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
public class KelurahanServiceImpl implements KelurahanService {
    private final KelurahanRepo kelurahanRepo;

    @Override
    public List<KelurahanRes> get() {
        List<KelurahanEntity> result = this.kelurahanRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }
        return result.stream().map(KelurahanRes::new).collect(Collectors.toList());
    }

    @Override
    public Optional<KelurahanRes> getById(String id) {
        KelurahanEntity result = this.kelurahanRepo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }
        KelurahanRes res = new KelurahanRes(result);
        return Optional.of(res);
    }

    @Override
    public Optional<KelurahanRes> save(KelurahanReq request) {
        KelurahanEntity result = new KelurahanEntity();

        request.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(request, result);
        try {
            this.kelurahanRepo.save(result);
            log.info("save kelurahan to database success");
            return Optional.of(new KelurahanRes(result));
        } catch (Exception e) {
            log.error("save kelurahan to database failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelurahanRes> update(KelurahanReq request, String id) {
        KelurahanEntity result = this.kelurahanRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("kelurahan with id {} not found", id);
            return Optional.empty();
        }
        BeanUtils.copyProperties(request, result);
        try {
            this.kelurahanRepo.save(result);
            log.info("update kelurahan to database success");
            return Optional.of(new KelurahanRes(result));
        } catch (Exception e) {
            log.error("update kelurahan to database failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<KelurahanRes> delete(String id) {
        KelurahanEntity result = this.kelurahanRepo.findById(id).orElse(null);
        if (result == null) {
            log.warn("kelurahan with id {} not found", id);
            return Optional.empty();
        }
        try {
            this.kelurahanRepo.delete(result);
            log.info("delete kelurahan to database success");
            return Optional.of(new KelurahanRes(result));
        } catch (Exception e) {
            log.error("delete kelurahan to database failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
