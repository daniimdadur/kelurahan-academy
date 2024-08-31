package org.security.kelurahanacademy.kelurahan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.security.kelurahanacademy.kelurahan.model.entity.KelurahanEntity;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanRes {
    private String id;
    private String name;
    private String kec;

    public KelurahanRes(KelurahanEntity kelurahanEntity) {
        BeanUtils.copyProperties(kelurahanEntity, this);
    }
}
