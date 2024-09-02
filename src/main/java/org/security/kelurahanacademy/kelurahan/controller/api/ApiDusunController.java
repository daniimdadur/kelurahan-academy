package org.security.kelurahanacademy.kelurahan.controller.api;

import org.security.kelurahanacademy.base.Response;
import org.security.kelurahanacademy.constant.MessageApp;
import org.security.kelurahanacademy.kelurahan.model.request.DusunReq;
import org.security.kelurahanacademy.kelurahan.model.response.DusunRes;
import org.security.kelurahanacademy.kelurahan.service.DusunService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/dusun")
public class ApiDusunController {
    private final DusunService dusunService;

    public ApiDusunController(DusunService dusunService) {
        this.dusunService = dusunService;
    }

    @GetMapping
    public ResponseEntity<Response> getDusun() {
        return ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.name())
                        .message(MessageApp.SUCCESS)
                        .data(dusunService.get())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getDusunById(@PathVariable("id") String id) {
        Optional<DusunRes> result = this.dusunService.getById(id);
        if (result.isPresent()) {
            DusunRes response = result.get();
            return ResponseEntity.ok(
                    Response.builder()
                            .code(HttpStatus.OK.value())
                            .status(HttpStatus.OK.name())
                            .message(MessageApp.SUCCESS)
                            .data(response)
                            .build());
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .code(HttpStatus.BAD_REQUEST.value())
                            .status(HttpStatus.BAD_REQUEST.name())
                            .message(MessageApp.NOT_FOUND)
                            .data(null)
                            .build()
            );
        }
    }

    @PostMapping
    public ResponseEntity<Response> postDusun(@RequestBody DusunReq request) {
        Optional<DusunRes> result = dusunService.save(request);
        if (result.isPresent()) {
            DusunRes response = result.get();
            return ResponseEntity.ok(
                    Response.builder()
                            .code(HttpStatus.OK.value())
                            .status(HttpStatus.OK.name())
                            .message(MessageApp.SUCCESS)
                            .data(response)
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
    public ResponseEntity<Response> updateDusun(@PathVariable("id") String id, @RequestBody DusunReq request) {
        Optional<DusunRes> result = this.dusunService.update(request, id);
        if (result.isPresent()) {
            DusunRes dusunRes= result.get();
            return ResponseEntity.ok(
                    Response.builder()
                            .code(HttpStatus.OK.value())
                            .status(HttpStatus.OK.name())
                            .message(MessageApp.SUCCESS)
                            .data(dusunRes)
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteDusun(@PathVariable("id") String id) {
        Optional<DusunRes> result = this.dusunService.delete(id);
        if (result.isPresent()) {
            DusunRes res = result.get();
            return ResponseEntity.ok(
                    Response.builder()
                            .code(HttpStatus.OK.value())
                            .status(HttpStatus.OK.name())
                            .message(MessageApp.DELETED)
                            .data(res)
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
}
