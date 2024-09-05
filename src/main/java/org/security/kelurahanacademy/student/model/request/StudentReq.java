package org.security.kelurahanacademy.student.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentReq {
    private String id;
    private Integer nis;
    private String firstName;
    private String lastName;
    private String address;
}
