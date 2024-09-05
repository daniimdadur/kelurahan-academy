package org.security.kelurahanacademy.student.controller.api;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.base.BaseController;
import org.security.kelurahanacademy.base.Response;
import org.security.kelurahanacademy.student.model.request.ClassReq;
import org.security.kelurahanacademy.student.model.response.ClassRes;
import org.security.kelurahanacademy.student.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/class")
public class ApiClassController extends BaseController<ClassRes> {
    private final ClassService classService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<ClassRes> result = this.classService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String id) {
        Optional<ClassRes> result = this.classService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody ClassReq request) {
        Optional<ClassRes> result = this.classService.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable String id, @RequestBody ClassReq request) {
        Optional<ClassRes> result = this.classService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        Optional<ClassRes> result = this.classService.delete(id);
        return getResponse(result);
    }
}
