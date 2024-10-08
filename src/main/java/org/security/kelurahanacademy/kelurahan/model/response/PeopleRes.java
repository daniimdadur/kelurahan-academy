package org.security.kelurahanacademy.kelurahan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.entity.PeopleEntity;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleRes {
    private String id;
    private Integer nik;
    private String name;
    private String gender;
    private String age;
    private String rtId;
    private String rtName;

    public PeopleRes(PeopleEntity peopleEntity) {
        BeanUtils.copyProperties(peopleEntity, this);
        this.rtId = peopleEntity.getRtId();
        this.rtName = peopleEntity.getRt().getName();
    }
}
