package org.security.kelurahanacademy.student.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryReq {
    private String id;
    private String schoolName;
    private String schoolAddress;
    private String schoolCity;
}
