package org.security.kelurahanacademy.kelurahan.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanReq {
    private String id;
    private String name;
    private String kec;
    private List<DusunReq> dusunList = new ArrayList<>();

    public KelurahanReq(String id, String name, String kec) {
        this.id = id;
        this.name = name;
        this.kec = kec;
    }
}
