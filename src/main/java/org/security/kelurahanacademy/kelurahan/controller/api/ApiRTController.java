package org.security.kelurahanacademy.kelurahan.controller.api;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.base.BaseController;
import org.security.kelurahanacademy.base.Response;
import org.security.kelurahanacademy.kelurahan.model.request.RTReq;
import org.security.kelurahanacademy.kelurahan.model.response.RTRes;
import org.security.kelurahanacademy.kelurahan.service.RTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rt")
public class ApiRTController extends BaseController<RTRes> {
    private final RTService rtService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<RTRes> result = this.rtService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String id) {
        Optional<RTRes> result = this.rtService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody RTReq request) {
        Optional<RTRes> result = this.rtService.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable String id, @RequestBody RTReq request) {
        Optional<RTRes> result = this.rtService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        Optional<RTRes> result = this.rtService.delete(id);
        return getResponse(result);
    }
}
