package org.security.kelurahanacademy.kelurahan.controller.api;

import org.security.kelurahanacademy.base.Response;
import org.security.kelurahanacademy.constant.MessageApp;
import org.security.kelurahanacademy.kelurahan.model.request.KelurahanReq;
import org.security.kelurahanacademy.kelurahan.model.response.KelurahanRes;
import org.security.kelurahanacademy.kelurahan.service.KelurahanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/kelurahan")
public class ApiKelurahanController {
    private final KelurahanService kelurahanService;

    @Autowired
    public ApiKelurahanController(KelurahanService kelurahanService) {
        this.kelurahanService = kelurahanService;
    }

    @GetMapping
    public ResponseEntity<Response> getKelurahan() {
        return ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.name())
                        .message(MessageApp.SUCCESS)
                        .data(kelurahanService.get())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getKelurahanById(@PathVariable("id") String id) {
        Optional<KelurahanRes> result = kelurahanService.getById(id);
        if (result.isPresent()) {
            KelurahanRes kelurahanRes = result.get();
            return ResponseEntity.ok(
                    Response.builder()
                            .code(HttpStatus.OK.value())
                            .status(HttpStatus.OK.name())
                            .message(MessageApp.SUCCESS)
                            .data(kelurahanRes)
                            .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .code(HttpStatus.NOT_FOUND.value())
                            .status(HttpStatus.NOT_FOUND.name())
                            .message(MessageApp.NOT_FOUND)
                            .data(null)
                            .build()
            );
        }
    }

    @PostMapping
    public ResponseEntity<Response> postKelurahan(@RequestBody KelurahanReq request) {
        Optional<KelurahanRes> result = this.kelurahanService.save(request);
        if (result.isPresent()) {
            KelurahanRes kelurahanRes = result.get();
            return ResponseEntity.ok(
                    Response.builder()
                            .code(HttpStatus.OK.value())
                            .status(HttpStatus.OK.name())
                            .message(MessageApp.SUCCESS)
                            .data(kelurahanRes)
                            .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .code(HttpStatus.BAD_REQUEST.value())
                            .status(HttpStatus.BAD_REQUEST.name())
                            .message(MessageApp.FAILED)
                            .data(null)
                            .build()
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateKelurahan(@PathVariable("id") String id, @RequestBody KelurahanReq request) {
        Optional<KelurahanRes> result = this.kelurahanService.update(request, id);
        if (result.isPresent()) {
            KelurahanRes kelurahanRes = result.get();
            return ResponseEntity.ok(
                    Response.builder()
                            .code(HttpStatus.OK.value())
                            .status(HttpStatus.OK.name())
                            .message(MessageApp.SUCCESS)
                            .data(kelurahanRes)
                            .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .code(HttpStatus.NOT_FOUND.value())
                            .status(HttpStatus.NOT_FOUND.name())
                            .message(MessageApp.FAILED)
                            .build()
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteKelurahan(@PathVariable("id") String id) {
        Optional<KelurahanRes> result = this.kelurahanService.delete(id);
        if (result.isPresent()) {
            KelurahanRes kelurahanRes = result.get();
            return ResponseEntity.ok(
                    Response.builder()
                            .code(HttpStatus.OK.value())
                            .status(HttpStatus.OK.name())
                            .message(MessageApp.DELETED)
                            .data(kelurahanRes)
                            .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .code(HttpStatus.NOT_FOUND.value())
                            .status(HttpStatus.NOT_FOUND.name())
                            .message(MessageApp.NOT_FOUND)
                            .data(null)
                            .build()
            );
        }
    }
}
