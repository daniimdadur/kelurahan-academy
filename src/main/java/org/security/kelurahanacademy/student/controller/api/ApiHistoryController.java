package org.security.kelurahanacademy.student.controller.api;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.base.BaseController;
import org.security.kelurahanacademy.base.Response;
import org.security.kelurahanacademy.student.model.request.HistoryReq;
import org.security.kelurahanacademy.student.model.response.HistoryRes;
import org.security.kelurahanacademy.student.service.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/history")
public class ApiHistoryController extends BaseController<HistoryRes> {
    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<HistoryRes> result = this.historyService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String id) {
        Optional<HistoryRes> result = this.historyService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody HistoryReq request) {
        Optional<HistoryRes> result = this.historyService.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable String id, @RequestBody HistoryReq request) {
        Optional<HistoryRes> result = this.historyService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        Optional<HistoryRes> result = this.historyService.delete(id);
        return getResponse(result);
    }
}
