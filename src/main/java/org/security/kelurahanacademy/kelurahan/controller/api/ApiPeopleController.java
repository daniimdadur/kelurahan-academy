package org.security.kelurahanacademy.kelurahan.controller.api;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.base.BaseController;
import org.security.kelurahanacademy.base.Response;
import org.security.kelurahanacademy.kelurahan.model.request.PeopleReq;
import org.security.kelurahanacademy.kelurahan.model.response.PeopleRes;
import org.security.kelurahanacademy.kelurahan.service.PeopleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/people")
public class ApiPeopleController extends BaseController<PeopleRes> {
    private final PeopleService peopleService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<PeopleRes> result = this.peopleService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String id) {
        Optional<PeopleRes> result = this.peopleService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody PeopleReq req) {
        Optional<PeopleRes> result = this.peopleService.save(req);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable("id") String id,
                                           @RequestBody PeopleReq req) {
        Optional<PeopleRes> result = this.peopleService.update(req, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        Optional<PeopleRes> result = this.peopleService.delete(id);
        return getResponse(result);
    }
}
