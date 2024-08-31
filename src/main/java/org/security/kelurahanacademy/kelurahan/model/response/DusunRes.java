package org.security.kelurahanacademy.kelurahan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.entity.DusunEntity;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DusunRes {
    private String id;
    private String name;
    private String dusunLeader;

    public DusunRes(DusunEntity dusunEntity) {
        BeanUtils.copyProperties(dusunEntity, this);
    }
}
