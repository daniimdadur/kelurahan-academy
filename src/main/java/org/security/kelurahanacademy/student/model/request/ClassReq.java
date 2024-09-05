package org.security.kelurahanacademy.student.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassReq {
    private String id;
    private String schoolClass;
    private LocalDate startDate;
    private LocalDate endDate;
    private String teacherName;
}
