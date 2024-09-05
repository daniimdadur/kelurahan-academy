package org.security.kelurahanacademy.kelurahan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.entity.RTEntity;
import org.security.kelurahanacademy.kelurahan.model.entity.RWEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RWRes {
    private String id;
    private String name;
    private String rwLeader;
    private List<RTRes> rtList = new ArrayList<>();

    public RWRes(RWEntity rwEntity) {
        BeanUtils.copyProperties(rwEntity, this);

        if (!rwEntity.getRtList().isEmpty()) {
            for (RTEntity entity : rwEntity.getRtList()) {
                this.rtList.add(new RTRes(entity));
            }
        }
    }
}
