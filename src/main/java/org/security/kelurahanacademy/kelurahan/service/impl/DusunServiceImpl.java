package org.security.kelurahanacademy.kelurahan.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.security.kelurahanacademy.kelurahan.model.entity.KelurahanEntity;
import org.security.kelurahanacademy.kelurahan.model.request.DusunReq;
import org.security.kelurahanacademy.kelurahan.model.response.DusunRes;
import org.security.kelurahanacademy.kelurahan.repo.DusunRepo;
import org.security.kelurahanacademy.kelurahan.repo.KelurahanRepo;
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
    private final KelurahanRepo kelurahanRepo;

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
            log.info("dusun with id {} not found", id);
            return Optional.empty();
        }

        DusunRes res = new DusunRes(result);
        return Optional.of(res);
    }

    @Override
    public Optional<DusunRes> save(DusunReq request) {
        KelurahanEntity kelurahan = this.kelurahanRepo.findById(request.getKelurahanId()).orElse(null);
        if (kelurahan == null) {
            return Optional.empty();
        }

        DusunEntity result = new DusunEntity();

        request.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(request, result);
        result.setKelurahan(kelurahan);
        try {
            this.dusunRepo.save(result);
            return Optional.of(new DusunRes(result));
        } catch (Exception e) {
            log.error("save dusun failed, error: {}", e.getMessage());
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
        KelurahanEntity kelurahanEntity = this.kelurahanRepo.findById(request.getKelurahanId()).orElse(null);
        result.setKelurahanId(kelurahanEntity.getId());

        try {
            this.dusunRepo.saveAndFlush(result);
            return Optional.of(new DusunRes(result));
        } catch (Exception e) {
            log.error("save dusun failed, error: {}", e.getMessage());
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

    private Optional<DusunRes> saveOrUpdate(DusunEntity result) {
        try {
            this.dusunRepo.saveAndFlush(result);
            return Optional.of(convertEntityToRes(result));
        } catch (Exception e) {
            log.error("save dusun failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    private DusunEntity getEntityById(String id) {
        DusunEntity result = this.dusunRepo.findById(id).orElse(null);
        if (result == null) {
            log.warn("dusun with id {} not found", id);
            return null;
        }

        return result;
    }

    private KelurahanEntity getKelurahanById(String id) {
        KelurahanEntity result = kelurahanRepo.findById(id).orElse(null);
        if (result == null) {
            log.warn("kelurahan with id {} not found", id);
            return null;
        }

        return result;
    }

    private DusunEntity convertReqToEntity(DusunReq request) {
        KelurahanEntity kelurahanEntity = this.kelurahanRepo.findById(request.getKelurahanId()).orElse(null);
        if (kelurahanEntity == null) {
            return null;
        }

        DusunEntity result = new DusunEntity();
        request.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(request, result);
        result.setKelurahan(kelurahanEntity);
        return result;
    }

    private DusunRes convertEntityToRes(DusunEntity entity) {
        DusunRes result = new DusunRes();

        BeanUtils.copyProperties(entity, result);
        if (entity.getKelurahan() != null) {
            result.setKelurahanId(entity.getKelurahan().getId());
            result.setKelurahanName(entity.getKelurahan().getName());
        }

        return result;
    }

    private void convertReqToEntity(DusunReq request, DusunEntity result) {
        BeanUtils.copyProperties(request, result);

        KelurahanEntity kelurahanEntity = this.kelurahanRepo.findById(request.getKelurahanId()).orElse(null);
        result.setKelurahanId(kelurahanEntity.getId());
    }
}
