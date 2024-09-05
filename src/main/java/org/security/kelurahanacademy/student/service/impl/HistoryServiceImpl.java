package org.security.kelurahanacademy.student.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.kelurahanacademy.student.model.entity.HistoryEntity;
import org.security.kelurahanacademy.student.model.request.HistoryReq;
import org.security.kelurahanacademy.student.model.response.HistoryRes;
import org.security.kelurahanacademy.student.repo.HistoryRepo;
import org.security.kelurahanacademy.student.service.HistoryService;
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
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepo historyRepo;

    @Override
    public List<HistoryRes> get() {
        List<HistoryEntity> result = this.historyRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        return result.stream().map(HistoryRes::new).collect(Collectors.toList());
    }

    @Override
    public Optional<HistoryRes> getById(String id) {
        HistoryEntity result = this.historyRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("history with id {} not found", id);
            return Optional.empty();
        }

        HistoryRes response = new HistoryRes(result);
        return Optional.of(response);
    }

    @Override
    public Optional<HistoryRes> save(HistoryReq request) {
        HistoryEntity result = new HistoryEntity();

        request.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(request, result);
        try {
            this.historyRepo.save(result);
            HistoryRes response = new HistoryRes(result);
            return Optional.of(response);
        } catch (Exception e) {
            log.error("save history failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<HistoryRes> update(HistoryReq request, String id) {
        HistoryEntity result = this.historyRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("history with id {} not found", id);
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, result);
        try {
            this.historyRepo.save(result);
            return Optional.of(new HistoryRes(result));
        } catch (Exception e) {
            log.error("update history failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<HistoryRes> delete(String id) {
        HistoryEntity result = this.historyRepo.findById(id).orElse(null);
        if (result == null) {
            log.warn("history with id {} not found", id);
            return Optional.empty();
        }

        try {
            this.historyRepo.delete(result);
            return Optional.of(new HistoryRes(result));
        } catch (Exception e) {
            log.error("delete history failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
