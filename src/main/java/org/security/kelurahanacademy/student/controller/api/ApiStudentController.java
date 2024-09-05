package org.security.kelurahanacademy.student.controller.api;

import lombok.RequiredArgsConstructor;
import org.security.kelurahanacademy.base.BaseController;
import org.security.kelurahanacademy.base.Response;
import org.security.kelurahanacademy.constant.MessageApp;
import org.security.kelurahanacademy.student.model.request.StudentReq;
import org.security.kelurahanacademy.student.model.response.StudentRes;
import org.security.kelurahanacademy.student.repo.StudentRepo;
import org.security.kelurahanacademy.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class ApiStudentController extends BaseController<StudentRes> {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Response> get() {
        return ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.name())
                        .message(MessageApp.SUCCESS)
                        .data(studentService.get())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String id) {
        Optional<StudentRes> result = this.studentService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody StudentReq request) {
        Optional<StudentRes> result = this.studentService.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@RequestBody StudentReq request,
                                           @PathVariable("id") String id) {
        Optional<StudentRes> result = this.studentService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        Optional<StudentRes> result = this.studentService.delete(id);
        return getResponse(result);
    }
}
