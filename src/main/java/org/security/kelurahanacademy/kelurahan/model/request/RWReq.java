package org.security.kelurahanacademy.kelurahan.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RWReq {
    private String id;
    private String name;
    private String rwLeader;
    private String dusunId;
    private List<RTReq> rtList = new ArrayList<>();

    public RWReq(String id, String name, String rwLeader, List<RTReq> rtList) {
        this.id = id;
        this.name = name;
        this.rwLeader = rwLeader;
        this.rtList = rtList;
    }

    public RWReq(String id, String name, String rwLeader, String dusunId) {
        this.id = id;
        this.name = name;
        this.rwLeader = rwLeader;
        this.dusunId = dusunId;
    }
}
