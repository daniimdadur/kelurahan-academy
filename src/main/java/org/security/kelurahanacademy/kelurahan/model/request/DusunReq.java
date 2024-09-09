package org.security.kelurahanacademy.kelurahan.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DusunReq {
    private String id;
    private String name;
    private String dusunLeader;
    private String kelurahanId;
    private List<RWReq> rwList = new ArrayList<>();

    public DusunReq(String id, String name, String dusunLeader, List<RWReq> rwList) {
        this.id = id;
        this.name = name;
        this.dusunLeader = dusunLeader;
        this.rwList = rwList;
    }

    public DusunReq(String id, String name, String dusunLeader, String kelurahanId) {
        this.id = id;
        this.name = name;
        this.dusunLeader = dusunLeader;
        this.kelurahanId = kelurahanId;
    }
}
