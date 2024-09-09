package org.security.kelurahanacademy.kelurahan.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RTReq {
    private String id;
    private String name;
    private String rtLeader;
    private String rwId;
    private List<PeopleReq> peopleList = new ArrayList<>();

    public RTReq(String id, String name, String rtLeader, List<PeopleReq> peopleList) {
        this.id = id;
        this.name = name;
        this.rtLeader = rtLeader;
        this.peopleList = peopleList;
    }

    public RTReq(String id, String name, String rtLeader, String rwId) {
        this.id = id;
        this.name = name;
        this.rtLeader = rtLeader;
        this.rwId = rwId;
    }
}
