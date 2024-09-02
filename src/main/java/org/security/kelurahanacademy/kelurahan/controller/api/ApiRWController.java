package org.security.kelurahanacademy.kelurahan.controller.api;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.base.BaseController;
import org.security.kelurahanacademy.base.Response;
import org.security.kelurahanacademy.kelurahan.model.request.RWReq;
import org.security.kelurahanacademy.kelurahan.model.response.RWRes;
import org.security.kelurahanacademy.kelurahan.service.RWService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rw")
public class ApiRWController extends BaseController<RWRes> {
    private final RWService rwService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<RWRes> result = this.rwService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable("id") String id) {
        Optional<RWRes> result = this.rwService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody RWReq request) {
        Optional<RWRes> result = this.rwService.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable("id") String id, @RequestBody RWReq req) {
        Optional<RWRes> result = this.rwService.update(req, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") String id) {
        Optional<RWRes> result = this.rwService.delete(id);
        return getResponse(result);
    }
}
