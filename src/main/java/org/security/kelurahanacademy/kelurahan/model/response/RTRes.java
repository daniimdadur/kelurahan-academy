package org.security.kelurahanacademy.kelurahan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.entity.RTEntity;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RTRes {
    private String id;
    private String name;
    private String rtLeader;

    public RTRes(RTEntity rtEntity) {
        BeanUtils.copyProperties(rtEntity, this);
    }
}
