package org.security.kelurahanacademy.kelurahan.controller;

import org.security.kelurahanacademy.base.Response;
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
                        .message("Success")
                        .data(kelurahanService.get())
                        .build()
        );
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
                            .message("Success")
                            .data(kelurahanRes)
                            .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .code(HttpStatus.BAD_REQUEST.value())
                            .status(HttpStatus.BAD_REQUEST.name())
                            .message("Bad Request")
                            .data(null)
                            .build()
            );
        }
    }
}
