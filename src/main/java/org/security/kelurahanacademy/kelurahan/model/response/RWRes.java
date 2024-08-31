package org.security.kelurahanacademy.kelurahan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.entity.RWEntity;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RWRes {
    private String id;
    private String name;
    private String rwLeader;

    public RWRes(RWEntity rwEntity) {
        BeanUtils.copyProperties(rwEntity, this);
    }
}
