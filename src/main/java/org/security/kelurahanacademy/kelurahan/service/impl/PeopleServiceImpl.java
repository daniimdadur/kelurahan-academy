package org.security.kelurahanacademy.kelurahan.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.kelurahanacademy.kelurahan.model.entity.PeopleEntity;
import org.security.kelurahanacademy.kelurahan.model.entity.RTEntity;
import org.security.kelurahanacademy.kelurahan.model.request.PeopleReq;
import org.security.kelurahanacademy.kelurahan.model.response.PeopleRes;
import org.security.kelurahanacademy.kelurahan.repo.PeopleRepo;
import org.security.kelurahanacademy.kelurahan.repo.RTRepo;
import org.security.kelurahanacademy.kelurahan.service.PeopleService;
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
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepo peopleRepo;
    private final RTRepo rtRepo;

    @Override
    public List<PeopleRes> get() {
        List<PeopleEntity> result = this.peopleRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }
        return result.stream().map(PeopleRes::new).collect(Collectors.toList());
    }

    @Override
    public Optional<PeopleRes> getById(String id) {
        PeopleEntity result = this.peopleRepo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }

        PeopleRes res = new PeopleRes(result);
        return Optional.of(res);
    }

    @Override
    public Optional<PeopleRes> save(PeopleReq request) {
        RTEntity rtEntity = this.rtRepo.findById(request.getRtId()).orElse(null);
        if (rtEntity == null) {
            return Optional.empty();
        }

        PeopleEntity result = new PeopleEntity();

        request.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(request, result);
        result.setRt(rtEntity);
        try {
            this.peopleRepo.save(result);
            log.info("save people success");
            return Optional.of(new PeopleRes(result));
        } catch (Exception e) {
            log.error("save people failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<PeopleRes> update(PeopleReq request, String id) {
        PeopleEntity result = this.peopleRepo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, result);
        try {
            this.peopleRepo.save(result);
            log.info("update people success");
            return Optional.of(new PeopleRes(result));
        } catch (Exception e) {
            log.error("update people failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<PeopleRes> delete(String id) {
        PeopleEntity result = this.peopleRepo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }

        try {
            this.peopleRepo.delete(result);
            log.info("delete people success");
            return Optional.of(new PeopleRes(result));
        } catch (Exception e) {
            log.error("delete people failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
