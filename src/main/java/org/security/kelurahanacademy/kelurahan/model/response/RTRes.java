package org.security.kelurahanacademy.kelurahan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.entity.PeopleEntity;
import org.security.kelurahanacademy.kelurahan.model.entity.RTEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RTRes {
    private String id;
    private String name;
    private String rtLeader;
    private String rwId;
    private String rwName;
    private List<PeopleRes> peopleList = new ArrayList<>();

    public RTRes(RTEntity rtEntity) {
        BeanUtils.copyProperties(rtEntity, this);
        this.rwId = rtEntity.getRwId();
        this.rwName = rtEntity.getRw().getName();

        if (!rtEntity.getPeopleList().isEmpty()) {
            for (PeopleEntity entity : rtEntity.getPeopleList()) {
                this.peopleList.add(new PeopleRes(entity));
            }
        }
    }
}
