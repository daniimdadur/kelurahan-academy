package org.security.kelurahanacademy.base;

import org.security.kelurahanacademy.constant.MessageApp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class BaseController<T> {
    public ResponseEntity<Response> getResponse(List<T> result) {
        return ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.name())
                        .message(MessageApp.SUCCESS)
                        .data(result)
                        .build()
        );
    }

    public ResponseEntity<Response> getResponse(Optional<T> result) {
        if (result.isPresent()) {
            T res = result.get();
            return ResponseEntity.ok(
                    Response.builder()
                            .code(HttpStatus.OK.value())
                            .status(HttpStatus.OK.name())
                            .message(MessageApp.SUCCESS)
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
